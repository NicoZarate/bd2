package bd2.Muber.interfaces.services;

import java.util.List;

import bd2.Muber.dto.ConductorDTO;


public interface ConductoresServiceBI extends BaseServiceBI{

	public List<ConductorDTO> getConductores();
	public ConductorDTO buscarConductor(Long id);
}
