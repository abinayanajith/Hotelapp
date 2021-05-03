package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Entity.Booking;
import Entity.Contract;
import Entity.Customer;
import Entity.OccupiedRoom;
import Entity.ReservedRoom;
import Entity.Room;
import Entity.RoomTypeAllocation;
import Entity.UnreservedRoom;
import Entity.roominter;
import dao.Bookingdao;
import dao.Contractdao;
import dao.Customerdao;
import dao.OccupiedRoomdao;
import dao.ReservedRoomdao;
import dao.RoomTypeAllocationdao;
import dao.Roomdao;
import dao.UnreservedRoomdao;
import dto.BookingRequest;
import dto.BookingResponse;
import dto.SearchRequest;
import dto.SearchResponse;
import model.RoomDetail;
import model.RoomResult;

@Service
public class AgentService {

	@Autowired
	public OccupiedRoomdao ord;
	@Autowired
	public ReservedRoomdao rrd;
	@Autowired
	public UnreservedRoomdao urd;
	@Autowired
	public Roomdao rd;
	@Autowired
	public RoomTypeAllocationdao rtad;
	@Autowired
	public Contractdao cd;
	@Autowired
	public Customerdao cusd;
	@Autowired
	public Bookingdao bd;
	
	public SearchResponse res;
	
	public SearchResponse getList(SearchRequest req) {
		Date start = req.start;
		int nights = req.nights;
		List<RoomDetail> roomDetail = req.roomDetails;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(start); // Using today's date
		c.add(Calendar.DATE, nights); // Adding 5 days
		Date end = c.getTime();
	
		List<Room> rooms = new ArrayList<Room>();
		List<OccupiedRoom> room1 = new ArrayList<OccupiedRoom>();
		List<ReservedRoom> room2 = new ArrayList<ReservedRoom>();
		List<UnreservedRoom> room3 = new ArrayList<UnreservedRoom>();
		
		List<roominter> roominterList = new ArrayList<roominter>();
		
		room1 = ord.getList(start);
		room2 = rrd.getList(start, end);
		room3 = urd.getList();
		
		for(OccupiedRoom r:room1) {
			//int allocationId = rd.getId(r.getId());
			//rooms.add(new Room(allocationId ));
			roominterList.add(r);
		}
		for(ReservedRoom r:room2) {
			//int allocationId = rd.getId(r.getId());
			//rooms.add(new Room(allocationId ));
			roominterList.add(r);
		}
		for(UnreservedRoom r:room3) {
			//int allocationId = rd.getId(r.getId());
			//rooms.add(new Room(allocationId ));
			roominterList.add(r);
		}
		
		
		
		res = new SearchResponse();
		
		for(RoomDetail r : roomDetail) {
			RoomResult result = new RoomResult();
			List<Room> roomDetailResult = new ArrayList<>();
			List<Integer> pricing = new ArrayList<>();
			
			int counter = 0;
			//Iterator<Room> itr = rooms.iterator();
			Iterator<roominter> itr = roominterList.iterator();
				while(itr.hasNext()) {
					roominter next = itr.next();
					int roomid = next.getId();
					Room room = rd.get(roomid);
					//int allocId = rd.getallocId(roomid);
					RoomTypeAllocation alloc = rtad.getAlloc(room.getAllocationId());
					Contract con = cd.getContract(alloc.getContractId());
					if(r.persons<=alloc.getMax()) {
						if(end.before(con.getEnd())) {
							roomDetailResult.add(room);
							pricing.add(alloc.getPrice());
							
							if(roomDetailResult.size()>= r.rooms) {
								break;
							}
						}
					}
					
				}
			if(roomDetailResult.size()>= r.rooms) {
				result.roomDetailResults = roomDetailResult;
				result.pricingResults = pricing;
			}
			res.results.add(result);
			
		}
		
		return res;
	
	}
	
	public BookingResponse bookRooms(BookingRequest req) {
		Date start = req.start;
		int nights = req.nights;
		String name = req.name;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(start); // Using today's date
		c.add(Calendar.DATE, nights); // Adding 5 days
		Date end = c.getTime();
	
		
		int customerId = cusd.addCustomer(new Customer(name,start,nights));
		int bookingId = bd.book(new Booking(customerId, 1));
		
		
		 
		 List<Integer> roomId = req.roomId;
		for(int i: roomId) {
			if(urd.getRoomNumbers().contains(i)) {//check if we could compare int with integer
				urd.removeRoom(i);
			}
		    rrd.addRoom(new ReservedRoom(i,bookingId, start, end));
		}
		
		
		
		BookingResponse res = new BookingResponse();
		res.status="success";
		return res;
		
	}
}
