package bd2.Muber.interfaces.services;

import java.util.List;

import bd2.Muber.model.Pasajero;

public interface PasajerosServiceBI extends BaseServiceBI{

	public List<Pasajero> getPasajeros();
}
