package bd2.Muber.services.impl;

import java.util.List;

import bd2.Muber.dto.PasajeroDTO;
import bd2.Muber.repositories.impl.HibernatePasajerosRepository;

public class PasajerosServiceImpl extends BaseServiceImpl{
	public HibernatePasajerosRepository pasajerosRepository;
	
	public List<PasajeroDTO> getPasajeros()
	{
		return this.dtoFactory.crearPasajerosDTO(pasajerosRepository.getPasajeros());
	}

	public HibernatePasajerosRepository getPasajerosRepository() {
		return pasajerosRepository;
	}

	public void setPasajerosRepository(HibernatePasajerosRepository pasajerosRepository) {
		this.pasajerosRepository = pasajerosRepository;
	}
}