package ar.com.plug.examen.domain.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.dao.ClienteDao;
import ar.com.plug.examen.domain.dao.TransaccionDao;
import ar.com.plug.examen.domain.dto.TransaccionAltaDto;
import ar.com.plug.examen.domain.dto.TransaccionUpdateDto;
import ar.com.plug.examen.domain.exception.ServiceException;
import ar.com.plug.examen.domain.model.Transaccion;
import ar.com.plug.examen.domain.service.TransaccionService;
import ar.com.plug.examen.domain.util.EstadoTransaccion;

@Service
public class TransaccionServiceImpl implements TransaccionService {

	@Autowired
	private TransaccionDao dao;
	
	@Autowired
	private ClienteDao clienteDao;

	@Override
	public List<Transaccion> getTransactions() {
		return dao.findAll();
	}

	@Override
	public Optional<Transaccion> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Transaccion createTransaction(TransaccionAltaDto source) {
		Transaccion entity = new Transaccion();
		entity.setEstado(EstadoTransaccion.EN_ESPERA.getDescription());
		entity.setCliente(clienteDao.findById(source.getIdCliente()).get());
		entity.setFecha(new Date());
		return dao.save(entity);
	}

	@Override
	public Transaccion updateTransaction(TransaccionUpdateDto source) {
		Transaccion entity = findById(source.getId()).get();
		if(Optional.ofNullable(source.getIdCliente()).isPresent()) 
			entity.setCliente(clienteDao.findById(source.getIdCliente()).get());
		if(Optional.ofNullable(source.getEstado()).isPresent())
			entity.setEstado(source.getEstado());
		
		return dao.save(entity);
	}

	@Override
	public void deleteTransaction(Integer id) {
		dao.deleteById(id);
	}
	
	public Transaccion approbe(Integer id) {
		if(!findById(id).get().getEstado().equals(EstadoTransaccion.EN_ESPERA.getDescription()))
			throw new ServiceException("La transaccion ya no puede ser aprobada");
		TransaccionUpdateDto request = new TransaccionUpdateDto();
		request.setId(id);
		request.setEstado(EstadoTransaccion.APROBADA.getDescription());
		Transaccion updatedTransaction = updateTransaction(request);
		return updatedTransaction;
	}
	
	public Transaccion cancel(Integer id) {
		if(!findById(id).get().getEstado().equals(EstadoTransaccion.EN_ESPERA.getDescription()))
			throw new ServiceException("La transaccion ya no puede ser cancelada");
		TransaccionUpdateDto request = new TransaccionUpdateDto();
		request.setId(id);
		request.setEstado(EstadoTransaccion.CANCELADA.getDescription());
		Transaccion updatedTransaction = updateTransaction(request);
		return updatedTransaction;
	}
	
}
