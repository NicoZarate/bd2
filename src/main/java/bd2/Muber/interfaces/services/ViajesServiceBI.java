package bd2.Muber.interfaces.services;

import java.util.List;

import bd2.Muber.dto.ViajeDTO;
import bd2.Muber.model.Viaje;


public interface ViajesServiceBI extends BaseServiceBI{

	public List<ViajeDTO> getViajes();
	public List<ViajeDTO> getViajesAbiertos();
	public ViajeDTO buscarViaje(Long id);
}
