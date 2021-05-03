package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Entity.Admin;
import Entity.Agent;
import dao.Admindao;
import dao.Agentdao;
import dto.LoginResponse;

@Service
public class AuthenticationService {

	@Autowired 
	public Admindao admin;
	@Autowired 
	public Agentdao agent;
	
	public LoginResponse validate(String user, String password) {
		LoginResponse lr = new LoginResponse();
		
		if(admin.getUser(user, password) instanceof Admin) {
			lr.userType="admin";
		}else if(agent.getUser(user, password) instanceof Agent) {
			lr.userType = "agent";
		}else {
			lr.userType ="invalid";
		}
		
		
		return lr;
	}
}
