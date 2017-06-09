package bd2.Muber.services;

import bd2.Muber.repositories.RepositoryLocator;
//el service locator mantiene una referencia atodos los servicios existentes
import bd2.Muber.services.impl.*;
public class ServiceLocator{
	
	static PasajerosServiceImpl pasajerosService;
	static ConductoresServiceImpl conductoresService;
	static CalificacionesServiceImpl calificacionesService;
	static ViajesServiceImpl viajesService;
	
	
	public static ServiceLocator getInstance(){
		
		return new ServiceLocator();
	}
	public static PasajerosServiceImpl getPasajerosService(){
		return pasajerosService;
	}
	
	public static CalificacionesServiceImpl getCalificacionesService(){
		return calificacionesService;
	}
	
	public static ViajesServiceImpl getViajesService(){
		return viajesService;
	}
	
	
	  public static ConductoresServiceImpl getConductoresService() {
			return conductoresService;
		}
		public static void setConductoresService(ConductoresServiceImpl conductoresService) {
			ServiceLocator.conductoresService = conductoresService;
		}
		public static void setPasajerosService(PasajerosServiceImpl pasajerosService) {
			ServiceLocator.pasajerosService = pasajerosService;
		}
		public static void setCalificacionesService(CalificacionesServiceImpl calificacionesService) {
			ServiceLocator.calificacionesService = calificacionesService;
		}
		public static void setViajesService(ViajesServiceImpl viajesService) {
			ServiceLocator.viajesService = viajesService;
		}
}


