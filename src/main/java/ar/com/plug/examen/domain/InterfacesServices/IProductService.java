package ar.com.plug.examen.domain.InterfacesServices;

import java.util.List;
import java.util.Optional;

import ar.com.plug.examen.domain.model.Product;

public interface IProductService {
	public List<Product>Listar();
	public Optional<Product>ListarId(int id);
	public int save(Product p );
	public void delete(int id);
}
