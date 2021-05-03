package dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Entity.Room;

@Repository
public class Roomdao extends Basedao<Room,Integer> {

	//@Autowired
	//private SessionFactory sessionFactory;
	
	public Roomdao() {
		super(Room.class);
	}
	
	@Transactional
	public int getId(int roomId) {
		
	}
	@Transactional
	public int addRoom(Room r) {
		return this.save(r);
	}
	@Transactional
	public int getallocId(int roomId) {
		
	}
}
