package ar.com.flexibility.examen.domain.service;
import java.util.List;
import ar.com.flexibility.examen.domain.model.Producto;

public interface ProductoService {
	  public List<Producto> findAll();
	  public void removeByIdProducto(Integer id);
	  public Producto save(Producto producto);
	  public Producto findByIdProducto(Integer id);
}
