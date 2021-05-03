package dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Entity.Agent;
import Entity.Room;

@Repository
public class Agentdao extends Basedao<Agent,Integer>{

	//@Autowired
	//private SessionFactory sessionFactory;
	
	public Agentdao() {
		super(Agent.class);
	}
	
	@Transactional
	public Agent getUser(String username,String password) {
		
	}
}
