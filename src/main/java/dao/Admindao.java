package dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Entity.Admin;
import Entity.Room;

@Repository
public class Admindao extends Basedao<Admin,Integer>{
	
	//@Autowired
	//private SessionFactory sessionFactory;
	
	public Admindao() {
		super(Admin.class);
	}
	
//	public Admindao(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}

	@Transactional
	public Admin getUser(String username, String password) {
		sessionFactory.getCurrentSession().s
	}
}
