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
	public DetalleFactura createInvoiceDetail(DetalleFacturaAltaDto dto) {
		DetalleFactura detalleFactura = new DetalleFactura();
		detalleFactura.setFactura(facturaDao.findById(dto.getIdFactura()).get());
		detalleFactura.setProducto(productoDao.findById(dto.getIdProducto()).get());
		detalleFactura.setCantidad(dto.getCantidad());
		return dao.save(detalleFactura);
	}

	@Override
	public DetalleFactura updateInvoiceDetail(DetalleFacturaUpdateDto dto) {
		DetalleFactura detalleFactura = dao.findById(dto.getId()).get();
		if(Optional.ofNullable(dto.getCantidad()).isPresent())
			detalleFactura.setCantidad(dto.getCantidad());
		if(Optional.ofNullable(dto.getIdFactura()).isPresent())
			detalleFactura.setFactura(facturaDao.findById(dto.getIdFactura()).get());
		if(Optional.ofNullable(dto.getIdProducto()).isPresent())
			detalleFactura.setProducto(productoDao.findById(dto.getIdProducto()).get());
		return dao.save(detalleFactura);
	}

	@Override
	public void deleteInvoiceDetail(Integer id) {
		dao.deleteById(id);
	}

}
