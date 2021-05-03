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

import Entity.Contract;
import Entity.ReservedRoom;
import Entity.Room;

@Repository
public class Contractdao extends Basedao<Contract,Integer>{

	//@Autowired
	//private SessionFactory sessionFactory;
	
	public Contractdao() {
		super(Contract.class);
	}
	
	@Transactional
	public Contract getContract(int conId) {
		return this.get(conId);
	}
	@Transactional
	public int addContract(Contract c) {
		return this.save(c);
	}
	@Transactional
	public List<Contract> getContractList(Date start, Date end){
		
		Session session = sessionFactory.openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Contract> criteriaQuery = criteriaBuilder.createQuery(Contract.class);
		Root<Contract> reservedRoomRoot = criteriaQuery.from(Contract.class);
		
		Predicate[] predicates = new Predicate[2];
		//predicates[0] = criteriaBuilder.greaterThanOrEqualTo(occupiedRoomRoot.get("checkoutDate"), start);
		predicates[0] = criteriaBuilder.greaterThanOrEqualTo(reservedRoomRoot.<Date> get("end"), end);
		predicates[1] = criteriaBuilder.lessThanOrEqualTo(reservedRoomRoot.<Date> get("start"), start);
		
		criteriaQuery.select(reservedRoomRoot).where(predicates);
		Query<Contract> query = session.createQuery(criteriaQuery);
		List<Contract> contractList = query.list();
		session.close();
		
		return contractList;
	}
}
