package bd2.Muber.services.impl;

import java.util.List;

import bd2.Muber.dto.PasajeroDTO;
import bd2.Muber.repositories.impl.HibernatePasajerosRepository;

public class PasajerosServiceImpl extends BaseServiceImpl{
	HibernatePasajerosRepository pasajerosRepository;
	
	public List<PasajeroDTO> getPasajeros()
	{
		return this.dtoFactory.crearPasajerosDTO(pasajerosRepository.getPasajeros());
	}
}