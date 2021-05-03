package dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Entity.RoomTypeAllocation;

@Repository
public class RoomTypeAllocationdao extends Basedao<RoomTypeAllocation,Integer>{

	//public RoomTypeAllocation alloc;
	//@Autowired
	//private SessionFactory sessionFactory;
	
	public RoomTypeAllocationdao() {
		super(RoomTypeAllocation.class);
	}
	
	@Transactional
	public RoomTypeAllocation getAlloc(int allocId) {
		return this.get(allocId);
	}
	@Transactional
	public int addAlloc(RoomTypeAllocation alloc) {
		return this.save(alloc);
	}
}
