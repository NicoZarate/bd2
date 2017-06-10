package bd2.Muber.interfaces.repositories;

import java.util.List;

import bd2.Muber.model.Conductor;

public interface ConductoresRepositoryBI extends BaseRepositoryBI{
	
	public List<Conductor> getConductores();
	public Conductor buscarConductor(Long id);
	
}