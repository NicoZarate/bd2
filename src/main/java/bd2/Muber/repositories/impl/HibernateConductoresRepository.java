package bd2.Muber.repositories.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bd2.Muber.model.*;
public class HibernateConductoresRepository extends BaseHibernateRepository{
	
	
	//lista de conductores
	public List<Conductor> getConductores(){
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
		List<Conductor> conductores= session.createQuery("from Conductor").list();
		t.commit();
		endSession(session);
		return conductores;
	}
	
	//retorna conductor buscado por el id
	public Conductor buscarConductor(Long id){
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
		Query query =session.createQuery("from conductor WHERE id_usuario = :id");
		query.setParameter("id", id);
		Conductor conductor = (Conductor) query.uniqueResult();
		t.commit();
		endSession(session);
		return conductor;
	}
	
	
	
	
}