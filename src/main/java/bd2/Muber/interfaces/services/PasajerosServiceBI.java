package bd2.Muber.interfaces.services;

import java.util.List;

import bd2.Muber.dto.PasajeroDTO;


public interface PasajerosServiceBI extends BaseServiceBI{

	public List<PasajeroDTO> getPasajeros();
}
