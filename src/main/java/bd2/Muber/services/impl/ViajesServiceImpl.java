package bd2.Muber.services.impl;

import java.util.List;
import bd2.Muber.dto.ViajeDTO;
import bd2.Muber.interfaces.services.ViajesServiceBI;
import bd2.Muber.repositories.impl.HibernateViajesRepository;

public class ViajesServiceImpl extends BaseServiceImpl implements ViajesServiceBI{

	
	public List<ViajeDTO> getViajes(){
		
		return this.dtoFactory.crearViajesDTO(viajesRepository.getViajes());
	}
	
    public List<ViajeDTO> getViajesAbiertos(){
		
		return this.dtoFactory.crearViajesDTO(viajesRepository.getViajesAbiertos());
	}
    
    public ViajeDTO buscarViaje(Long id){
    	
    	//falta crear el dtoFactory.crearViajeDTO para que convierta
    	return null;
    }
}
