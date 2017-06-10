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


import bd2.Muber.model.*;
import bd2.Muber.services.*;
import bd2.Muber.dto.*;
import java.util.List;
@ControllerAdvice
@RequestMapping("/services")
@ResponseBody
@EnableWebMvc
public class MuberRestController {

	
	//listar todos los pasajeros
	@RequestMapping(value = "/pasajeros", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String listarPasajeros() {
		
		List<PasajeroDTO> pasajeros = ServiceLocator.getPasajerosService().getPasajeros();
		Map<Long, String> aMap = new HashMap<Long, String>();
        for (PasajeroDTO elem : pasajeros) {
			aMap.put(elem.getId_usuario(), elem.getNombre());
		}
		
		return new Gson().toJson(aMap);
		
	}
	
	//listar todos los conductores
		@RequestMapping(value = "/conductores", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
		public String listarConductores() {
			List<ConductorDTO> conductores = ServiceLocator.getConductoresService().getConductores();
			Map<Long, String> aMap = new HashMap<Long, String>();
			for (ConductorDTO elem : conductores) {
				aMap.put(elem.getId_usuario(), elem.getNombre());
			}
		  return new Gson().toJson(aMap);
		}
		
		
		//listar todos los datos del conductor
		@RequestMapping(value = "/conductores/detalle/{id_conductor}", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
		public String infoConductor(@PathVariable("id_conductor") long id_conductor) {
			//FaLTA VER QUE LISTE LOS VIAJES Y EL ProMEDIO
			try{ 
				ConductorDTO c = ServiceLocator.getConductoresService().buscarConductor(id_conductor);
				return new Gson().toJson(this.mostrarInformacion(c) );
			 } catch(NullPointerException e)
            {
                return "no hay conductor con ese id";
            } 
		}
		
		//listar todos los viajes abiertos
		@RequestMapping(value = "/viajes/abiertos", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
		public String listarViajesAbiertos() {
			try{ 
			    List<ViajeDTO> viajes = ServiceLocator.getViajesService().getViajesAbiertos();
				Map<Long, Object> aMap = new HashMap<Long, Object>();
		        for (ViajeDTO elem : viajes) {
				    	aMap.put(elem.getId_viaje() , this.viajeInfo(elem));
				}
				
				return new Gson().toJson(aMap);
			 } catch(NullPointerException e)
            {
                return "no hay viajes abiertos";
            } 
		}
				
		//crear un viaje
				
//curl -X POST -d "origen=MardelPlata&destino=Cordoba&costoTotal=2500&cantidadPasajeros=3&conductorId=1" http://localhost:8080/MuberRESTful/rest/services/viajes/nuevo
		
		@RequestMapping(value = "/viajes/nuevo", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
		public Long crearViaje(
				@RequestParam("origen") String  origen,
				@RequestParam("destino") String  destino,
				@RequestParam("conductorId") Long  conductorId,
				@RequestParam("costoTotal") float  costoTotal,
				@RequestParam("cantidadPasajeros") int  cantidadPasajeros){
			    ConductorDTO conductor = ServiceLocator.getConductoresService().buscarConductor(conductorId);
			    if(conductor == null){
			    	return new Long(0);
			    }
				Long id = ServiceLocator.getViajesService().cargarViaje(origen, destino, cantidadPasajeros, costoTotal, conductorId);
				
				return id ;
				
			
	    }
		//curl -X PUT -d "idViaje=4" http://localhost:8080/MuberRESTful/rest/services/viajes/finalizar -G
		
		
		@RequestMapping(value = "/viajes/finalizar", method = RequestMethod.PUT, produces = "application/json", headers = "Accept=application/json")
		public String finalizarViaje(@RequestParam("idViaje") Long idViaje){
					    
			      return ServiceLocator.getViajesService().finalizarViaje(idViaje);
					
			    }
		
		//cargar credito a un pasajero
				
		//curl -X PUT -d "monto=3000&idPasajero=2" http://localhost:8080/MuberRESTful/rest/services/pasajeros/cargarCredito -G
				
				
		@RequestMapping(value = "/pasajeros/cargarCredito", method = RequestMethod.PUT, produces = "application/json", headers = "Accept=application/json")
		public String agregarCredito(
				@RequestParam("monto") Long monto,
				@RequestParam("idPasajero") Long idPasajero){
						
				  return ServiceLocator.getPasajerosService().agregarCredito(idPasajero,monto);
					
				}
		//agregar pasajero a un viaje	
		
		//curl -X PUT -d "idViaje=4&idPasajero=2" http://localhost:8080/MuberRESTful/rest/services/viajes/agregarPasajero -G
				
		@RequestMapping(value = "/viajes/agregarPasajero", method = RequestMethod.PUT, produces = "application/json", headers = "Accept=application/json")
		public String agregarPasajeroAViaje(
						@RequestParam("idViaje") Long idViaje,
						@RequestParam("idPasajero") Long idPasajero) {
						
					   	return ServiceLocator.getPasajerosService().agregarPasajeroAViaje(idViaje,idPasajero);
										

			    }
		/*	
		
		
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
		protected Map<String, Object> mostrarInformacion(ConductorDTO c){
			Map<String, Object> info = new HashMap<String, Object>();
			Map<String, Object> listaDeViajes = new HashMap<String, Object>();
			info.put("Nombre", c.getNombre());
			info.put("Clave", c.getContrasenia());
			info.put("Fecha de licencia", c.getF_licencia());
			info.put("Fecha de ingreso", c.getF_ingreso());
			
		    info.put("Calificacion promedio", c.getPromedio());
		    Collection <ViajeDTO> viajes= c.getViajes();
			for (ViajeDTO elem : viajes) {
				listaDeViajes.put("viaje "+ elem.getId_viaje(), this.viajeInfo(elem));
			}
			info.put("Lista de viajes", listaDeViajes);
			
			return info;	
			}
			
		protected Map<String, Object> viajeInfo(ViajeDTO v){
			Map<String, Object> mapViaje = new HashMap<String, Object>();
			mapViaje.put("max pasajero", v.getMax_pasajeros());
			mapViaje.put("origen", v.getOrigen());
			mapViaje.put("destino", v.getDestino());
			mapViaje.put("costo", v.getCosto());
			return mapViaje;	
			}
		
}

