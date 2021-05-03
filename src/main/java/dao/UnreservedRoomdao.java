package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Entity.UnreservedRoom;

@Repository
public class UnreservedRoomdao extends Basedao<UnreservedRoom,Integer> {

	//@Autowired
	//private SessionFactory sessionFactory;
	
	public UnreservedRoomdao() {
		super(UnreservedRoom.class);
	}
	
	@Transactional
	public List<UnreservedRoom> getList(){
		return this.getAll();
		//@SuppressWarnings("unchecked")
		//List<UnreservedRoom> roomList = (List<UnreservedRoom>) sessionFactory.getCurrentSession().getCriteriaBuilder().createQuery(UnreservedRoom.class).;
	}
	@Transactional
	public List<Integer> getRoomNumbers(){
		int myInt = 1;
		List<Integer> list = new ArrayList<Integer>();
		list.add(myInt);
	
	}
	@Transactional
	public void removeRoom(int id) {
		this.delete(id);
	}
	@Transactional
	public void addRoom(UnreservedRoom r) {
		 this.save(r);
	}
}
