package ar.com.flexibility.examen.domain.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.flexibility.examen.domain.model.Comprobante;
import ar.com.flexibility.examen.domain.service.ComprobanteService;
import ar.com.flexibility.examen.repository.ComprobanteRepository;

@Service
public class ComprobanteServiceImpl implements ComprobanteService{
	@Autowired
	private ComprobanteRepository comprobanteRepository;
	
	@Override
	public List<Comprobante> findAll() {
		return (List<Comprobante>) comprobanteRepository.findAll();
	}

	@Override
	public void removeByIdComprobante(Integer id) {
		comprobanteRepository.delete(id);
	}

	@Override
	public Comprobante findByIdComprobante(Integer id) {
		return comprobanteRepository.findOne(id);
	}

	@Override
	public Comprobante save(Comprobante comprobante) {
		return comprobanteRepository.save(comprobante);
	}


}
