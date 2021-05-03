package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dto.NewHotelResponse;
import dto.NewHotelResquest;
import dto.ViewHotelRequest;
import dto.ViewHotelResponse;
import service.AdminService;
import service.AgentService;

@RestController
public class AdminController {

	@Autowired
	AdminService admin;
	
	@RequestMapping(value="/agent/addHotel", method = RequestMethod.POST)
	public NewHotelResponse addHotel( NewHotelResquest req) {
		
		return admin.addHotel(req);
	}
	
	@RequestMapping(value="/agent/viewContract", method = RequestMethod.POST)
	public ViewHotelResponse addHotel( ViewHotelRequest req) {
		
		return admin.viewHotel(req);
	}
}
