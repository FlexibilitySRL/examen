package ar.com.plug.examen.domain.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.dao.ClienteDao;
import ar.com.plug.examen.domain.dao.FacturaDao;
import ar.com.plug.examen.domain.dto.FacturaAltaDto;
import ar.com.plug.examen.domain.dto.FacturaUpdateDto;
import ar.com.plug.examen.domain.exception.ServiceException;
import ar.com.plug.examen.domain.model.Factura;
import ar.com.plug.examen.domain.service.FacturaService;
import ar.com.plug.examen.domain.util.EstadoFactura;

@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	private FacturaDao dao;
	
	@Autowired
	private ClienteDao clienteDao;

	@Override
	public List<Factura> getInvoices() {
		return dao.findAll();
	}

	@Override
	public Optional<Factura> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Factura createInvoice(FacturaAltaDto source) {
		Factura entity = new Factura();
		entity.setEstado(EstadoFactura.EN_ESPERA.getDescription());
		entity.setCliente(clienteDao.findById(source.getIdCliente()).get());
		entity.setFecha(new Date());
		return dao.save(entity);
	}

	@Override
	public Factura updateInvoice(FacturaUpdateDto source) {
		Factura entity = findById(source.getId()).get();
		if(Optional.ofNullable(source.getIdCliente()).isPresent()) 
			entity.setCliente(clienteDao.findById(source.getIdCliente()).get());
		if(Optional.ofNullable(source.getEstado()).isPresent())
			entity.setEstado(source.getEstado());
		
		return dao.save(entity);
	}

	@Override
	public void deleteInvoice(Integer id) {
		dao.deleteById(id);
	}
	
	public Factura approbe(Integer id) {
		if(!findById(id).get().getEstado().equals(EstadoFactura.EN_ESPERA.getDescription()))
			throw new ServiceException("La factura ya no puede ser aprobada");
		FacturaUpdateDto request = new FacturaUpdateDto();
		request.setId(id);
		request.setEstado(EstadoFactura.APROBADA.getDescription());
		Factura updatedInvoice = updateInvoice(request);
		return updatedInvoice;
	}
	
	public Factura cancel(Integer id) {
		if(!findById(id).get().getEstado().equals(EstadoFactura.EN_ESPERA.getDescription()))
			throw new ServiceException("La factura ya no puede ser cancelada");
		FacturaUpdateDto request = new FacturaUpdateDto();
		request.setId(id);
		request.setEstado(EstadoFactura.CANCELADA.getDescription());
		Factura updatedInvoice = updateInvoice(request);
		return updatedInvoice;
	}
	
}
