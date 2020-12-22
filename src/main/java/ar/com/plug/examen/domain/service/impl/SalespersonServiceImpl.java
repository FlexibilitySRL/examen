package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Salesperson;
import ar.com.plug.examen.domain.repository.SalespersonRepository;
import ar.com.plug.examen.domain.service.SalespersonService;
import ar.com.plug.examen.exception.WithOperationsException;
import ar.com.plug.examen.exception.SalespersonNotFoundException;

@Service
public class SalespersonServiceImpl implements SalespersonService {
	@Autowired
	private SalespersonRepository salespersonRepository;
	
	
	@Override
	public Salesperson getOne(long id) {
		return salespersonRepository.findById(id).orElseThrow(() -> new SalespersonNotFoundException(id));
	}

	@Override
	public List<Salesperson> getAll() {
		return salespersonRepository.findAll();
	}

	@Override
	public Salesperson add(Salesperson salesperson) {
		return salespersonRepository.save(salesperson);
	}

	@Override
	public Salesperson modify(Salesperson salesperson) {
		Salesperson salespersonFounded = salespersonRepository.findById(salesperson.getId()).orElseThrow(() -> new SalespersonNotFoundException(salesperson.getId()));
		if (!salespersonFounded.getOperations().isEmpty())
			throw new WithOperationsException();
		return salespersonRepository.save(salesperson);
	}

	@Override
	public void delete(long id) {
		Salesperson salespersonFounded = salespersonRepository.findById(id).orElseThrow(() -> new SalespersonNotFoundException(id));
		if (!salespersonFounded.getOperations().isEmpty())
			throw new WithOperationsException();
		salespersonRepository.deleteById(id);
	}

}
