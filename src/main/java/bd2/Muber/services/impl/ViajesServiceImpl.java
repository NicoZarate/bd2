package bd2.Muber.services.impl;

import java.util.List;
import bd2.Muber.dto.ViajeDTO;
import bd2.Muber.repositories.impl.HibernateViajesRepository;

public class ViajesServiceImpl extends BaseServiceImpl{

HibernateViajesRepository viajesRepository;
	
	public List<ViajeDTO> getPasajeros(){
		
		return this.dtoFactory.crearViajesDTO(viajesRepository.getViajes());
	}
}
