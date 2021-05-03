package dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Entity.Booking;
import Entity.Room;

@Repository
public class Bookingdao extends Basedao<Booking,Integer>{

	//@Autowired
	//private SessionFactory sessionFactory;
	
	public Bookingdao() {
		super(Booking.class);
	}
	
	@Transactional
	public int book(Booking book) {
		int id = (int)sessionFactory.getCurrentSession().save(book);
		return id;
	}
}
