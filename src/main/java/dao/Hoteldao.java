package dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Entity.Hotel;
import Entity.Room;

@Repository
public class Hoteldao extends Basedao<Hotel,Integer>{

	//@Autowired
	//private SessionFactory sessionFactory;
	
	public Hoteldao() {
		super(Hotel.class);
	}
	
	@Transactional
	public Hotel getHotel(String name, String country, String city) {
		
	}
	@Transactional
	public int addHotel(Hotel h) {
		return this.save(h);
	}
}
