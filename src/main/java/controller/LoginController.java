package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dto.LoginRequest;
import dto.LoginResponse;
import service.AuthenticationService;

@RestController
public class LoginController {
	
	@Autowired
	public AuthenticationService auth;

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public LoginResponse validate(@RequestBody LoginRequest req ) {
		
		return auth.validate(req.username, req.password);
	}
}
