package dao;

import java.util.Date;
import java.util.List;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Entity.OccupiedRoom;
import Entity.Room;

@Repository
public class OccupiedRoomdao extends Basedao<OccupiedRoom,Integer>{

	//@Autowired
	//private SessionFactory sessionFactory;
	
	public OccupiedRoomdao() {
		super(OccupiedRoom.class);
	}
	
	@Transactional
	public List<OccupiedRoom> getList(Date start){
		Session session = sessionFactory.openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<OccupiedRoom> criteriaQuery = criteriaBuilder.createQuery(OccupiedRoom.class);
		Root<OccupiedRoom> occupiedRoomRoot = criteriaQuery.from(OccupiedRoom.class);
		
		Predicate[] predicates = new Predicate[1];
		//predicates[0] = criteriaBuilder.greaterThanOrEqualTo(occupiedRoomRoot.get("checkoutDate"), start);
		predicates[0] = criteriaBuilder.greaterThanOrEqualTo(occupiedRoomRoot.<Date> get("checkoutDate"), start);
		
		criteriaQuery.select(occupiedRoomRoot).where(predicates);
		Query<OccupiedRoom> query = session.createQuery(criteriaQuery);
		List<OccupiedRoom> roomList = query.list();
		session.close();
		
		return roomList;
		
	}
}
