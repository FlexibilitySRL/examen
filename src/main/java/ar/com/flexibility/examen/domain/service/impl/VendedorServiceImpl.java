package ar.com.flexibility.examen.domain.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.flexibility.examen.domain.model.Vendedor;
import ar.com.flexibility.examen.domain.service.VendedorService;
import ar.com.flexibility.examen.repository.VendedorRepository;

@Service
public class VendedorServiceImpl implements VendedorService{
	@Autowired
	private VendedorRepository vendedorRepository;
	
	@Override
	public List<Vendedor> findAll() {
		return (List<Vendedor>) vendedorRepository.findAll();
	}

	@Override
	public void removeByIdVendedor(Integer id) {
		vendedorRepository.delete(id);
	}

	@Override
	public Vendedor findByIdVendedor(Integer id) {
		return vendedorRepository.findOne(id);
	}

	@Override
	public Vendedor save(Vendedor vendedor) {
		return vendedorRepository.save(vendedor);
	}

}
