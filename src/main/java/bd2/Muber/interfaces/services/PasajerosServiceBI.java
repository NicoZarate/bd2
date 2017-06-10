package bd2.Muber.interfaces.services;

import java.util.List;

import bd2.Muber.dto.PasajeroDTO;


public interface PasajerosServiceBI extends BaseServiceBI{

	public List<PasajeroDTO> getPasajeros();

	public String agregarCredito(Long idPasajero, Long monto);

	public String agregarPasajeroAViaje(Long idViaje, Long idPasajero);
}
