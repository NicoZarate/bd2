package bd2.Muber.interfaces.repositories;
import org.hibernate.Session;

abstract interface BaseRepositoryBI{
	public Session getSession();
	public void cargarBaseEtapa1();
	public void cargarBaseEtapa2();
	
}