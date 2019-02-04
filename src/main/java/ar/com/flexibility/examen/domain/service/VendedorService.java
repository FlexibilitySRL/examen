package ar.com.flexibility.examen.domain.service;
import java.util.List;
import ar.com.flexibility.examen.domain.model.Vendedor;

public interface VendedorService {
	  public List<Vendedor> findAll();
	  public void removeByIdVendedor(Integer id);
	  public Vendedor findByIdVendedor(Integer id);
	  public Vendedor save(Vendedor vendedor);
}
