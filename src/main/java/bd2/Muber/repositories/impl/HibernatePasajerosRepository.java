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

	@Override
	public String agregarCredito(Long idPasajero, Long monto) {
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
		Query query =session.createQuery("from Pasajero WHERE id_usuario = :id");
		query.setParameter("id", idPasajero);
		Pasajero pasajero = (Pasajero) query.uniqueResult();
		if(pasajero == null){
			return "no se encotro al pasajero con ese id";
		}
		pasajero.cargarCredito(monto);
		t.commit();
		endSession(session);
		return "Se cargo saldo con exito a "+ pasajero.getNombre();
	}

	@Override
	public String agregarPasajeroAViaje(Long idViaje, Long idPasajero) {
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
		Query query =session.createQuery("from Viaje WHERE id_viaje = :id");
		query.setParameter("id", idViaje);
		Viaje viaje = (Viaje) query.uniqueResult();
		if(viaje==null){return "viaje con existe con ese id";}
	    query =session.createQuery("from Pasajero WHERE id_usuario = :id");
		query.setParameter("id", idPasajero);
		Pasajero pasajero = (Pasajero) query.uniqueResult();
		if(pasajero == null ){return "pasajero no existe con ese id";}
		String s = pasajero.agregarse(viaje);
		t.commit();
		endSession(session);
		return s;
	}

	
	
}