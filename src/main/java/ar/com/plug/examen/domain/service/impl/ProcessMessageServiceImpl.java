package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Message;
import ar.com.plug.examen.domain.model.entity.Cliente;
import ar.com.plug.examen.domain.model.entity.Compra;
import ar.com.plug.examen.domain.model.entity.Producto;
import ar.com.plug.examen.domain.model.repository.ClienteRepository;
import ar.com.plug.examen.domain.service.ProcessMessageService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessMessageServiceImpl implements ProcessMessageService {

	

	@Autowired
	private ClienteRepository clienteRepository;
	
	


    @Override
    public Message processMessage(String message) {
        return new Message(message);
    }

	@Override
	public boolean init() {
	
		Cliente cliente = new Cliente();
		Compra compra = new Compra();
		
		Producto producto1 = new Producto();
		producto1.setNombreProducto("producto1");	
		producto1.setPrecio(1L);
		Producto producto2 = new Producto();
		producto2.setNombreProducto("producto2");	
		producto2.setPrecio(2L);
		
		compra.addProducto(producto1);
		compra.addProducto(producto2);
		compra.setFecha(new Date());

		cliente.addCompra(compra);
		cliente.setNombre("Cliente1");
		
		clienteRepository.save(cliente);
		
		return true;
	}
}
