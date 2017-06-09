package bd2.Muber.services.impl;

import bd2.Muber.dto.*;
import bd2.Muber.repositories.impl.HibernatePasajerosRepository;

public  class BaseServiceImpl{
	
	protected DTOFactory dtoFactory;
	protected HibernatePasajerosRepository pasajerosRepository;
	
	
	public HibernatePasajerosRepository getPasajerosRepository() {
		return pasajerosRepository;
	}

	public void setPasajerosRepository(HibernatePasajerosRepository pasajerosRepository) {
		this.pasajerosRepository = pasajerosRepository;
	}

	public DTOFactory getDtoFactory() {
		return dtoFactory;
	}

	public void setDtoFactory(DTOFactory dtoFactory) {
		this.dtoFactory = dtoFactory;
	}
	
}