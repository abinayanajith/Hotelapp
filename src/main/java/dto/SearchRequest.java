package dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.RoomDetail;

public class SearchRequest {

	public Date start ;
	public int nights ;
	//public List<RoomDetail> roomDetails = new ArrayList<RoomDetail>();
	public List<RoomDetail> roomDetails;
}
