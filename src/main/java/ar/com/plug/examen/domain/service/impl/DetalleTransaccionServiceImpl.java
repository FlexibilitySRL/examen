package ar.com.plug.examen.domain.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.dao.DetalleTransaccionDao;
import ar.com.plug.examen.domain.dao.TransaccionDao;
import ar.com.plug.examen.domain.dao.ProductoDao;
import ar.com.plug.examen.domain.dto.DetalleTransaccionAltaDto;
import ar.com.plug.examen.domain.dto.DetalleTransaccionUpdateDto;
import ar.com.plug.examen.domain.model.DetalleTransaccion;
import ar.com.plug.examen.domain.service.DetalleTransaccionService;

@Service
public class DetalleTransaccionServiceImpl implements DetalleTransaccionService {

	@Autowired
	private DetalleTransaccionDao dao;
	
	@Autowired
	private TransaccionDao transaccionDao;
	
	@Autowired
	private ProductoDao productoDao;
	
	@Override
	public Optional<DetalleTransaccion> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public DetalleTransaccion createTransactionDetail(DetalleTransaccionAltaDto source) {
		DetalleTransaccion entity = new DetalleTransaccion();
		entity.setTransaccion(transaccionDao.findById(source.getIdTransaccion()).get());
		entity.setProducto(productoDao.findById(source.getIdProducto()).get());
		entity.setCantidad(source.getCantidad());
		return dao.save(entity);
	}

	@Override
	public DetalleTransaccion updateTransactionDetail(DetalleTransaccionUpdateDto source) {
		DetalleTransaccion entity = dao.findById(source.getId()).get();
		if(Optional.ofNullable(source.getCantidad()).isPresent())
			entity.setCantidad(source.getCantidad());
		if(Optional.ofNullable(source.getIdTransaccion()).isPresent())
			entity.setTransaccion(transaccionDao.findById(source.getIdTransaccion()).get());
		if(Optional.ofNullable(source.getIdProducto()).isPresent())
			entity.setProducto(productoDao.findById(source.getIdProducto()).get());
		return dao.save(entity);
	}

	@Override
	public void deleteTransactionDetail(Integer id) {
		dao.deleteById(id);
	}

}
