package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dto.BookingRequest;
import dto.BookingResponse;
import dto.LoginRequest;
import dto.SearchRequest;
import dto.SearchResponse;
import service.AgentService;

@RestController
public class AgentController {

	@Autowired
	AgentService agent;
	
	@RequestMapping(value="/agent/searchRooms", method = RequestMethod.POST)
	public SearchResponse searchRooms(@RequestBody SearchRequest req) {
		
		return agent.getList(req);
	}
	
	@RequestMapping(value="/agent/bookRooms", method = RequestMethod.POST)
	public BookingResponse bookRooms(@RequestBody BookingRequest req) {
		
		return agent.bookRooms(req);
	}
}
