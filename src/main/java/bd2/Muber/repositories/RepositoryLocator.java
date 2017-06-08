package bd2.Muber.repositories;

import bd2.Muber.repositories.impl.*;

public class RepositoryLocator{
	// devuelven instacias de los repositorios
	static HibernateCalificacionesRepository calificacionesRepository;
	static HibernatePasajerosRepository pasajerosRepository;
	static HibernateConductoresRepository conductoresRepository;
	static HibernateViajesRepository viajesRepository;
	
	public static HibernateCalificacionesRepository getCalificacionesRepository(){
		
		return calificacionesRepository;
		
	}
	
    public static HibernateConductoresRepository getConductoresRepository(){
		
		return conductoresRepository;
		
	}

    public static HibernatePasajerosRepository getPasajerosRepository(){
	
	    return pasajerosRepository;
	
    }

    public static HibernateViajesRepository getViajesRepository(){
	
	   return viajesRepository;
	
    }
}