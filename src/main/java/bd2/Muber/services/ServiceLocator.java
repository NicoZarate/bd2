package bd2.Muber.services;

//el service locator mantiene una referencia atodos los servicios existentes
import bd2.Muber.services.impl.*;
public class ServiceLocator{
	
	static PasajerosServiceImpl pasajerosService;
	static ConductoresServiceImpl conductoresService;
	static CalificacionesServiceImpl calificacionesService;
	static ViajesServiceImpl viajesService;
	
	public static PasajerosServiceImpl getPasajerosService(){
		return pasajerosService;
	}
	
	public static CalificacionesServiceImpl getCalificacionesService(){
		return calificacionesService;
	}
	
	public static ViajesServiceImpl getViajesService(){
		return viajesService;
	}
}


