package bd2.Muber.repositories.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bd2.Muber.dto.ConductorDTO;
import bd2.Muber.dto.DTOFactory;
import bd2.Muber.interfaces.repositories.ConductoresRepositoryBI;
import bd2.Muber.model.*;
public class HibernateConductoresRepository extends BaseHibernateRepository implements ConductoresRepositoryBI{
	
	
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
	
	public ConductorDTO buscarConductorDTO(Long id){
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
		Query query =session.createQuery("from Conductor WHERE id_usuario = :id");
		query.setParameter("id", id);
		Conductor conductor = (Conductor) query.uniqueResult();
		ConductorDTO conductordto = new DTOFactory().crearConductorDTO(conductor);
		t.commit();
		endSession(session);
		return conductordto;
	}
	
	public Conductor buscarConductor(Long id){
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
		Query query =session.createQuery("from Conductor WHERE id_usuario = :id");
		query.setParameter("id", id);
		Conductor conductor = (Conductor) query.uniqueResult();
		t.commit();
		endSession(session);
		return null;
	}

	public Map<Object, Object> getTop10() {
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
/*		SQLQuery query = session.createSQLQuery(
				SELECT AVG(puntaje) as promedio, c.nombre as nombre 
				FROM Conductor c 
				inner join Viaje v on v.id_conductor = c.id_usuario 
				INNER join Calificacion ca on ca.id_viaje = v.id_viaje 
				group by c.nombre order by promedio DESC);
*/		
		Query query= session.createQuery("select new map(avg(puntaje) as promedio, c.nombre as nombre)"
				+ "										from Conductor c, Viaje v, Calificacion ca "
				+ "										where v.id_conductor = c.id_usuario and"
				+ "										ca.id_viaje = v.id_viaje"
				+ "										group by nombre"
				+ "										order by promedio desc");
		
		query.setMaxResults(10);
		List<Map<Object, Object>> list = query.list();
		
		
		t.commit();
		
		endSession(session);
		
		//ver como devolver el map
		
		
		Map<Object, Object> map;
		
		for (Map<Object, Object> obj : list) {	  
			map.put(obj.getKey(), obj.getValue());
			}
		return map;
		//Query query = session.createQuery("from Cat cat,Owner owner where cat.OwnerId = owner.Id and owner.Name='Duke'");
	}
	
	
}