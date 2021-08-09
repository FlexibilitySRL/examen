package ar.com.plug.examen.domain.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.dao.DetalleFacturaDao;
import ar.com.plug.examen.domain.dao.FacturaDao;
import ar.com.plug.examen.domain.dao.ProductoDao;
import ar.com.plug.examen.domain.dto.DetalleFacturaAltaDto;
import ar.com.plug.examen.domain.dto.DetalleFacturaUpdateDto;
import ar.com.plug.examen.domain.model.DetalleFactura;
import ar.com.plug.examen.domain.service.DetalleFacturaService;

@Service
public class DetalleFacturaServiceImpl implements DetalleFacturaService {

	@Autowired
	private DetalleFacturaDao dao;
	
	@Autowired
	private FacturaDao facturaDao;
	
	@Autowired
	private ProductoDao productoDao;
	
	@Override
	public Optional<DetalleFactura> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public DetalleFactura createInvoiceDetail(DetalleFacturaAltaDto source) {
		DetalleFactura entity = new DetalleFactura();
		entity.setFactura(facturaDao.findById(source.getIdFactura()).get());
		entity.setProducto(productoDao.findById(source.getIdProducto()).get());
		entity.setCantidad(source.getCantidad());
		return dao.save(entity);
	}

	@Override
	public DetalleFactura updateInvoiceDetail(DetalleFacturaUpdateDto source) {
		DetalleFactura entity = dao.findById(source.getId()).get();
		if(Optional.ofNullable(source.getCantidad()).isPresent())
			entity.setCantidad(source.getCantidad());
		if(Optional.ofNullable(source.getIdFactura()).isPresent())
			entity.setFactura(facturaDao.findById(source.getIdFactura()).get());
		if(Optional.ofNullable(source.getIdProducto()).isPresent())
			entity.setProducto(productoDao.findById(source.getIdProducto()).get());
		return dao.save(entity);
	}

	@Override
	public void deleteInvoiceDetail(Integer id) {
		dao.deleteById(id);
	}

}
