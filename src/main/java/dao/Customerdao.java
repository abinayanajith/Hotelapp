package dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Entity.Customer;
import Entity.Room;

@Repository
public class Customerdao extends Basedao<Customer,Integer>{

	//@Autowired
	//private SessionFactory sessionFactory;
	
	public Customerdao() {
		super(Customer.class);
	}
	
	@Transactional
	public int addCustomer(Customer c) {
		//int id = (int)sessionFactory.getCurrentSession().save(c);   //https://stackoverflow.com/questions/25561681/hibernate-get-id-after-save-object
		
		//return id;
		return this.save(c);
		
	}
}
