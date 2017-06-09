package bd2.Muber.services.impl;

import java.util.List;

import bd2.Muber.model.Conductor;
import bd2.Muber.repositories.impl.HibernateConductoresRepository;

public class ConductoresServiceImpl extends BaseServiceImpl{

HibernateConductoresRepository conductoresRepository;
	
	public List<Conductor> getConductores()
	{
		return conductoresRepository.getConductores();
	}
}
