package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class Basedao<S, T extends Serializable> {

	private final Class<S> entityclass;
	
	@Autowired
	SessionFactory sessionFactory;
	
	public Basedao(Class<S> entityclass) {
		this.entityclass = entityclass;
	}
	
	public T save(S s) {
		
		return (T) sessionFactory.getCurrentSession().save(s);
	}
	
	public S get(T t) {
		@SuppressWarnings("unchecked")
		Session session = sessionFactory.openSession();
		S s = session.get(entityclass, t);
		session.close();
		return s;
	}
	
	public void update(S s) {
		sessionFactory.getCurrentSession().update(s);
		
	}
	
	public List<S> getAll(){
		@SuppressWarnings("unchecked")
		Session session = sessionFactory.openSession();
		
		List<S> all = session.createQuery("from " + entityclass.getName(),entityclass).getResultList();
		
		session.close();
		
		return all;
	}
	
	public S delete(T t) {
		S s = get(t);
		
		if(s != null) {
			sessionFactory.getCurrentSession().delete(s);
		}
		return s;
	}
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
