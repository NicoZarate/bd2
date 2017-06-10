package bd2.Muber.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bd2.Muber.interfaces.repositories.PasajerosRepositoryBI;
import bd2.Muber.model.*;
public class HibernatePasajerosRepository extends BaseHibernateRepository implements PasajerosRepositoryBI{
	
	
	//lista los pasajeros
	public List<Pasajero> getPasajeros(){
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
		List<Pasajero> pasajeros= session.createQuery("from Pasajero").list();
		t.rollback();
		endSession(session);
		return pasajeros;
	}
	
	//retorna pasajero buscado por el id
	public Pasajero buscarPasajero(Long id){
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
		Query query =session.createQuery("from Pasajero WHERE id_usuario = :id");
		query.setParameter("id", id);
		Pasajero pasajero = (Pasajero) query.uniqueResult();
		t.commit();
		endSession(session);
		return pasajero;
	}

	
	
}