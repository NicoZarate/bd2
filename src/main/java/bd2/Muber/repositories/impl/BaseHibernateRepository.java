package bd2.Muber.repositories.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BaseHibernateRepository{
	
	protected SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		SessionFactory factory = sessionFactory;
		Session session = factory.openSession();
		return session;
	        
	}
	
	protected void endSession(Session session){	
       	session.disconnect();
    	session.close();
}
	
	
	
}