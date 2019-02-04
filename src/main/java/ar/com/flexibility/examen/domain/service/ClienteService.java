package ar.com.flexibility.examen.domain.service;
import java.util.List;
import ar.com.flexibility.examen.domain.model.Cliente;

public interface ClienteService {

	  public Cliente save(Cliente c);
	  public List<Cliente> findAll();
	  public void removeByid(int id);
	  public Cliente findById(Integer id);
	
}
