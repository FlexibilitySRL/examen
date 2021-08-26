package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Message;
import ar.com.plug.examen.domain.repository.ProductosRepository;
import ar.com.plug.examen.domain.service.ProcessMessageService;


import org.springframework.stereotype.Service;

@Service
public class ProcessMessageServiceImpl implements ProcessMessageService {

	ProductosRepository productosRepository;

	public ProcessMessageServiceImpl(ProductosRepository productosRepository) {
		this.productosRepository = productosRepository;
	}

	@Override
	public Message processMessage(String message) {
		return new Message(message);
	}
	


	
}
