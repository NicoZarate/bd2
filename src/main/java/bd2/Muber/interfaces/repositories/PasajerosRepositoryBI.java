package bd2.Muber.interfaces.repositories;

import java.util.List;

import bd2.Muber.model.Pasajero;

public interface PasajerosRepositoryBI extends BaseRepositoryBI{
	
	public List<Pasajero> getPasajeros();
	public Pasajero buscarPasajero(Long id);
	
}