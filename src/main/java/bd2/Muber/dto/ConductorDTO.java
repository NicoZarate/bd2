package bd2.Muber.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import bd2.Muber.model.Viaje;
import bd2.Muber.model.Conductor;
public class ConductorDTO extends UsuarioDTO {
	private Set<Viaje> viajes;
	private Date f_licencia;
	

    public ConductorDTO(){

    }
    
    public ConductorDTO(String nombre, String contrasenia , Date f_licencia){
		
		super(nombre, contrasenia);
		this.viajes = new HashSet<Viaje>();
		this.f_licencia=f_licencia;
			
	}
    
    public ConductorDTO(Conductor c){
    	this.setId_usuario(c.getId_usuario());
		this.setNombre(c.getNombre());
		this.setContrasenia(c.getContrasenia());
		this.setF_ingreso(c.getF_ingreso());
    	
    }
    

    //getters y setters
    
	public Set<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(Set<Viaje> viajes) {
		this.viajes = viajes;
	}

	public Date getF_licencia() {
		return f_licencia;
	}

	public void setF_licencia(Date f_licencia) {
		this.f_licencia = f_licencia;
	}

	

	

}
