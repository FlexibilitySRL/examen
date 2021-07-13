package ar.com.plug.examen.domain.InterfacesServices;

import java.util.List;
import java.util.Optional;

import ar.com.plug.examen.domain.model.Customer;

public interface ICustomerService {
	public List<Customer>Listar();
	public Optional<Customer>ListarId(int id);
	public int save(Customer c );
	public void delete(int id);
}
