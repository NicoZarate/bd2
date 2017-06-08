package bd2.Muber.repositories.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bd2.Muber.interfaces.repositories.ViajesRepositoryBI;
import bd2.Muber.model.*;
public class HibernateViajesRepository extends BaseHibernateRepository implements ViajesRepositoryBI{
	
	
	//lista viajes
	public List<Viaje> getViajes(){
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
		List<Viaje> viajes= session.createQuery("from Viaje").list();
		t.commit();
		endSession(session);	
		return viajes;
	}
	//lista viajes abiertos
	public List<Viaje> getViajesAbiertos(){
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
		List<Viaje> viajes= session.createQuery("from Viaje WHERE finalizado = 0").list();
		t.commit();
		endSession(session);
		return viajes;
	}
	//retorna viaje buscado por el id
	
	public Viaje buscarViaje(Long id){
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
		Query query =session.createQuery("from Viaje WHERE id_viaje = :id");
		query.setParameter("id", id);
		Viaje viaje = (Viaje) query.uniqueResult();
		t.commit();
		endSession(session);
		return viaje;
	}
	
	// crea un viaje
	public boolean cargarViaje(String origen,String destino , float costoTotal ,int cantidadPasajeros,long conductorId){
		try{
		    Session session = this.getSession();
			Transaction t = session.beginTransaction();
			Conductor conductor = new HibernateConductoresRepository().buscarConductor(conductorId);
			
			//conductor.registrarViaje(origen, destino, cantidadPasajeros, costoTotal);
			t.commit();
			endSession(session);
			return true;
		}catch(Exception e){
			
			return false;
			
		}
	}
	
	
	
	
	
	
	
	
}