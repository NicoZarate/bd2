package bd2.Muber.services.impl;

import java.util.List;

import bd2.Muber.dto.PasajeroDTO;
import bd2.Muber.interfaces.repositories.PasajerosRepositoryBI;
import bd2.Muber.interfaces.services.PasajerosServiceBI;


public class PasajerosServiceImpl extends BaseServiceImpl implements  PasajerosServiceBI{
	

	public List<PasajeroDTO> getPasajeros()
	{
		return this.dtoFactory.crearPasajerosDTO(pasajerosRepository.getPasajeros());
	}

	
}