package bd2.Muber.services.impl;

import java.util.List;

import bd2.Muber.model.Pasajero;
import bd2.Muber.repositories.impl.HibernatePasajerosRepository;

public class PasajerosServiceImpl
{
	HibernatePasajerosRepository pasajerosRepository;
	
	public List<Pasajero> getPasajeros()
	{
		return pasajerosRepository.getPasajeros();
	}
}