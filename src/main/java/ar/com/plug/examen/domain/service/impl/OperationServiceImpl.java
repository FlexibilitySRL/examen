package ar.com.plug.examen.domain.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.constant.Constant;
import ar.com.plug.examen.domain.model.Operation;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.OperationRepository;
import ar.com.plug.examen.domain.service.CustomerService;
import ar.com.plug.examen.domain.service.OperationService;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.exception.OperationNotFoundException;

@Service
public class OperationServiceImpl implements OperationService {
	@Autowired
	OperationRepository operationRepository;
		
	@Autowired 
	CustomerService customerService;
	
	@Autowired
	ProductService productService;
	
	@Override
	public Operation getOne(long id) {
		return operationRepository.findById(id).orElseThrow(() -> new OperationNotFoundException(id));
	}

	@Override
	public List<Operation> getAll() {
		return operationRepository.findAll();
	}

	@Override
	public Operation add(Operation operation) {
		customerService.getOne(operation.getCustomer().getId());
		operation.getProducts().forEach(p -> productService.getOne(p.getId()));
		
		operation.setTotal();
		operation.setDate(LocalDateTime.now());
		operation.setState(Constant.NOT_APPROVED);
		Operation newOperation = operationRepository.save(operation);
		for (Product p : operation.getProducts()) {
			p.setOperation(newOperation);
			productService.modify(p);
		}
		//newOperation.getProducts().forEach(p -> p.setOperation(newOperation));	 
		return newOperation;
	}

	@Override
	public Operation aprove(long id) {
		Operation operation = operationRepository.findById(id).orElseThrow(() -> new OperationNotFoundException(id));
		operation.setState(Constant.APPROVED);
		return operationRepository.save(operation);
	}

}
