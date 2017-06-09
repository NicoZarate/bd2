package bd2.Muber.services.impl;

import java.util.List;

import bd2.Muber.model.Viaje;
import bd2.Muber.repositories.impl.HibernateViajesRepository;

public class ViajesServiceImpl extends BaseServiceImpl{

HibernateViajesRepository viajesRepository;
	
	public List<Viaje> getPasajeros()
	{
		return viajesRepository.getViajes();
	}
}
