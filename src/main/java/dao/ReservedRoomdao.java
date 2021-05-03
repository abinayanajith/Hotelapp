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
import Entity.ReservedRoom;
import Entity.Room;

@Repository
public class ReservedRoomdao extends Basedao<ReservedRoom,Integer> {

	
	//@Autowired
	//private SessionFactory sessionFactory;
	
	public ReservedRoomdao() {
		super(ReservedRoom.class);
	}
	
	@Transactional
	public List<ReservedRoom> getList(Date start, Date end){
		Session session = sessionFactory.openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<ReservedRoom> criteriaQuery = criteriaBuilder.createQuery(ReservedRoom.class);
		Root<ReservedRoom> reservedRoomRoot = criteriaQuery.from(ReservedRoom.class);
		
		Predicate[] predicates = new Predicate[2];
		//predicates[0] = criteriaBuilder.greaterThanOrEqualTo(occupiedRoomRoot.get("checkoutDate"), start);
		predicates[0] = criteriaBuilder.greaterThanOrEqualTo(reservedRoomRoot.<Date> get("checkoutDate"), start);
		predicates[1] = criteriaBuilder.lessThanOrEqualTo(reservedRoomRoot.<Date> get("checkinDate"), end);
		
		criteriaQuery.select(reservedRoomRoot).where(predicates);
		Query<ReservedRoom> query = session.createQuery(criteriaQuery);
		List<ReservedRoom> roomList = query.list();
		session.close();
		
		return roomList;
	}
	@Transactional
	public int addRoom(ReservedRoom r) {
		return this.save(r);
	}
}
