package bd2.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;

import antlr.collections.List;
import bd2.Muber.model.*;
import bd2.Muber.services.*;


@ControllerAdvice
@RequestMapping("/services")
@ResponseBody
@EnableWebMvc
public class MuberRestController {

	public Session getSession() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		return session;
	        
	}
	
	//listar todos los pasajeros
	@RequestMapping(value = "/pasajeros", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String listarPasajeros() {
		/*try{
			Map<Long, String> aMap = new HashMap<Long, String>();
			Session session = this.getSession();
			//Muber muber1= new Muber();
			//session.save(muber1);
		//	Muber muber = (Muber) session.get(Muber.class, new Long(1));
			Collection<Pasajero> pasajeros = muber.getPasajeros();
			
			for (Pasajero elem : pasajeros) {
				aMap.put(elem.getId_usuario(), elem.getNombre());
			}
			return new Gson().toJson(aMap);
		 } catch(NullPointerException e)
	        {
	            return "no hay pasajeros en la base de datos";
	        }*/
		//
		//ServiceLocator.getPasajerosService().getPasajeros();
		//nos falta declarar los beans de cada clase
		//Session session = this.getSession();
		//Muber muber = (Muber) session.get(Muber.class, new Long(1));
		Map<String, String> aMap = new HashMap<String, String>();
		aMap.put("", "");
		return new Gson().toJson(aMap);
		//   PasajerosServiceBI.getPasajerosService()
	}
	//listar todos los conductores
		@RequestMapping(value = "/conductores", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
		public String listarConductores() {
		  return null;
		}
		
		
		//listar todos los datos del conductor
		@RequestMapping(value = "/conductores/detalle/{id_conductor}", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
		public String infoConductor(@PathVariable("id_conductor") long id_conductor) {
			/*try{ 
			Session session = this.getSession();
			Conductor c = (Conductor) session.get(Conductor.class, id_conductor);
 			return new Gson().toJson(this.mostrarInformacion(c) );
			 } catch(NullPointerException e)
            {
                return "no hay conductor con ese id";
            } 
           */
			return null;
		}
		
		//listar todos los viajes abiertos
	/*	@RequestMapping(value = "/viajes/abiertos", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
		public String listarViajesAbiertos() {
            try{
            	Map<String, Object> aMap = new HashMap<String, Object>();
    			Session session = this.getSession();
    			Muber muber = (Muber) session.get(Muber.class, new Long(1));
    			//Collection<Conductor> c = muber.getConductores();
    			for(Conductor c: muber.getConductores()){	
    				for (Viaje v: c.getViajes()){
    					 if(v.getFinalizado()==false){	
    						 aMap.put("viaje "+ v.getId_viaje(), this.viajeInfo(v) );}
    			    }	
    	       }	
    			return new Gson().toJson(aMap);
            } catch(NullPointerException e)
            {
                return "no hay viajes abiertos";
            }
			
		}
				
		//crear un viaje
				
				
			
			
		@RequestMapping(value = "/viajes/nuevo", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
		public Long crearViaje(
				@RequestParam("origen") String  origen,
				@RequestParam("destino") String  destino,
				@RequestParam("conductorId") Long  conductorId,
				@RequestParam("costoTotal") float  costoTotal,
				@RequestParam("cantidadPasajeros") int  cantidadPasajeros){
				Session session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				Conductor conductor = (Conductor) session.get(Conductor.class, conductorId);
				Viaje v = conductor.registrarViaje(origen, destino, cantidadPasajeros, costoTotal);
				HibernateUtil.cerrar(session);
				return v.getId_viaje();
				
			
	    }
		
		//agregar pasajero a un viaje	
		
		//curl -X PUT -d "idViaje=3&idPasajero=2" http://localhost:8080/MuberRESTful/rest/services/viajes/agregarPasajero -G
		
		// cambie a post 
		@RequestMapping(value = "/viajes/agregarPasajero", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
		public String agregarPasajero(
				@RequestParam("idViaje") Long id_viaje,
				@RequestParam("idPasajero") Long id_pasajero) {
				
			    Session session = this.getSession();
			    Transaction t = session.beginTransaction();
				
				Muber muber = (Muber) session.get(Muber.class, new Long(1));
				Viaje v = (Viaje) session.get(Viaje.class, id_viaje);
				Pasajero p = (Pasajero) session.get(Pasajero.class, id_pasajero);
				p.agregarse(v);
				session.save(muber);
				t.commit();
				session.close();
				
				return "Se HA agregado el pasajero/a "+ p.getNombre() +" correctamente";
			
			
							

	    }
		
		// crear una calificacion de parte de un pasajero 
		

		//curl -X POST -d "idViaje=3&idPasajero=2&comentario=rapido&puntaje=3" http://localhost:8080/MuberRESTful/rest/services/viajes/calificar
		
		
		@RequestMapping(value = "/viajes/calificar", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
		public String agregarCalificacion(
				@RequestParam("idViaje") Long id_viaje,
				@RequestParam("idPasajero") Long id_pasajero,
         		@RequestParam("puntaje") int puntaje,
               @RequestParam("comentario") String comentario){
				
			    Session session = this.getSession();
			    Transaction t = session.beginTransaction();
				
				Muber muber = (Muber) session.get(Muber.class, new Long(1));
				Viaje v = (Viaje) session.get(Viaje.class, id_viaje);
				Pasajero p = (Pasajero) session.get(Pasajero.class, id_pasajero);
				p.calificar(puntaje,comentario,v);
				
				session.save(muber);
				t.commit();
				session.close();
				
				return "Se ha agregado la calificacion del pasajero/a " + p.getNombre();
			
			
							

	    }
		
		//cargar credito a un pasajero
		
		//curl -X PUT -d "monto=3000&idPasajero=2" http://localhost:8080/MuberRESTful/rest/services/pasajeros/cargarCredito -G
		
		
		@RequestMapping(value = "/pasajeros/cargarCredito", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
		public String agregarCredito(
				@RequestParam("monto") Long monto,
				@RequestParam("idPasajero") Long id_pasajero){
				
			    Session session = this.getSession();
			    Transaction t = session.beginTransaction();
				
				Muber muber = (Muber) session.get(Muber.class, new Long(1));
				Pasajero p = (Pasajero) session.get(Pasajero.class, id_pasajero);
			 	p.cargarCredito(monto);
				
				session.save(muber);
				t.commit();
				session.close();
				
				return "Se ha cargado credito correctamente a " + p.getNombre() ;
			
			
							

	    }
				
				//cargar credito a un pasajero
				
				//curl -X PUT -d "idViaje=4" http://localhost:8080/MuberRESTful/rest/services/viajes/finalizar -G
				
				
		@RequestMapping(value = "/viajes/finalizar", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
		public String finalizarViaje(
				@RequestParam("idViaje") Long id_viaje){
				
			    Session session = this.getSession();
			    Transaction t = session.beginTransaction();
				Muber muber = (Muber) session.get(Muber.class, new Long(1));
				Viaje v = (Viaje) session.get(Viaje.class, id_viaje);
				v.finalizar();
				
				session.save(muber);
				t.commit();
				session.close();
				
				return "Se ha finalizado el viaje";
			
	    }
						
		@RequestMapping(value = "/conductores/top10", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
		public String conductoresTop10(){
			Map<String, Object> aMap = new HashMap<String, Object>();
			Session session = this.getSession();
			Muber muber = (Muber) session.get(Muber.class, new Long(1));
			
			ArrayList<Conductor> top10 = muber.topTen();
			
			int i=1;
			for(Conductor c: top10){

				aMap.put("Puesto"+ i + ":" + c.getNombre(), "Promedio:" + c.calificacionPromedio() );
				i++;
			}
			if (top10.size()==0){
				return "no hay";
			}
			else{
				return new Gson().toJson(aMap);
			}
		}
		
				
		*/
		//metodos
		protected Map<String, Object> mostrarInformacion(Conductor c){
			Map<String, Object> info = new HashMap<String, Object>();
			Map<String, Object> listaDeViajes = new HashMap<String, Object>();
			info.put("Nombre", c.getNombre());
			info.put("Fecha de licencia", c.getF_licencia());
			//por alguna razon bardea el promedio
		    info.put("Calificacion promedio", c.calificacionPromedio());
			Collection <Viaje> viajes= c.getViajes();
			for (Viaje elem : viajes) {
				listaDeViajes.put("viaje "+ elem.getId_viaje(), this.viajeInfo(elem));
			}
			info.put("Lista de viajes", listaDeViajes);
			
			return info;	
			}
		protected Map<String, Object> viajeInfo(Viaje v){
			Map<String, Object> mapViaje = new HashMap<String, Object>();
			mapViaje.put("max pasajero", v.getMax_pasajeros());
			mapViaje.put("origen", v.getOrigen());
			mapViaje.put("destino", v.getDestino());
			mapViaje.put("costo", v.getCosto());
			return mapViaje;	
			}
}

