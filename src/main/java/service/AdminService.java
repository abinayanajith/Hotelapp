package service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Entity.Contract;
import Entity.Hotel;
import Entity.Room;
import Entity.RoomTypeAllocation;
import Entity.UnreservedRoom;
import dao.Contractdao;
import dao.Hoteldao;
import dao.RoomTypeAllocationdao;
import dao.Roomdao;
import dao.UnreservedRoomdao;
import dto.NewHotelResponse;
import dto.NewHotelResquest;
import dto.ViewHotelRequest;
import dto.ViewHotelResponse;
import model.NewRoomDetail;

@Service
public class AdminService {
	
	@Autowired
	public Contractdao cd;
	@Autowired
	public Hoteldao hd;
	@Autowired
	public RoomTypeAllocationdao rtad;
	@Autowired
	public Roomdao rd;
	@Autowired
	public UnreservedRoomdao urd;
	
	public NewHotelResponse addHotel(NewHotelResquest req) {
		String name = req.name;
		String city = req.city;
		String country = req.country;
		Date start = req.start;
		Date end = req.end;
		List<NewRoomDetail> detail = req.roomDetails;
		
		int hotelId;
		int contractId;
		
		if(hd.getHotel(name, country, city) instanceof Hotel) {
			hotelId = hd.getHotel(name, country, city).getHotelId();
		}
		else {
			hd.addHotel(new Hotel(name, country, city));
			hotelId = hd.getHotel(name, country, city).getHotelId();
		}
		
		contractId = cd.addContract(new Contract(hotelId, start, end));
		
		for(NewRoomDetail r : detail) {
			int rtaId = rtad.addAlloc( new RoomTypeAllocation(contractId, r.roomType, r.price, r.totalRooms, r.maxAdults));   //hotelId
			
			for(int i= 0; i<r.totalRooms;i++) {
				int roomId = rd.addRoom(new Room(rtaId));
				urd.addRoom(new UnreservedRoom (roomId));
			}
			
		}
		
		NewHotelResponse res = new NewHotelResponse();
		res.status="success";
		return res;
		
	}
	
	public ViewHotelResponse viewHotel(ViewHotelRequest req) {
		Date start = req.start;
		Date end = req.end;
		
		List<Contract> contractList = cd.getContractList(start, end);
		
		ViewHotelResponse res = new ViewHotelResponse();
		res.contractList = contractList;
		
		return res;
	}

}
