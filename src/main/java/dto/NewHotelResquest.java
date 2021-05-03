package dto;

import java.util.Date;
import java.util.List;

import model.NewRoomDetail;


public class NewHotelResquest {

	public Date start ;
	public Date end ;
	public String name;
	public String city;
	public String country;
	//public List<RoomDetail> roomDetails = new ArrayList<RoomDetail>();
	public List<NewRoomDetail> roomDetails;
}
