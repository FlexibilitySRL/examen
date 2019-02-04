package ar.com.flexibility.examen.domain.service;
import java.util.List;
import ar.com.flexibility.examen.domain.model.Comprobante;

public interface ComprobanteService {
	  public List<Comprobante> findAll();
	  public Comprobante save(Comprobante comprobante);
	  public void removeByIdComprobante(Integer id);
	  public Comprobante findByIdComprobante(Integer id);
}
