package ar.com.plug.examen.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.business.domain.Compra;
import ar.com.plug.examen.business.domain.CompraDetalle;
import ar.com.plug.examen.data.entity.Factura;
import ar.com.plug.examen.data.entity.FacturaDetalle;
import ar.com.plug.examen.data.repository.ClienteRepository;
import ar.com.plug.examen.data.repository.FacturaDetalleRepository;
import ar.com.plug.examen.data.repository.FacturaRepository;
import ar.com.plug.examen.data.repository.ProductoRepository;
import ar.com.plug.examen.data.repository.VendedorRepository;

@Service
public class FacturaService {	

	public static final Logger logger = Logger.getLogger(FacturaService.class);

	private final ClienteRepository clienteRepository;
	private final ProductoRepository productoRepository;
	private final VendedorRepository vendedorRepository;
	private final FacturaRepository facturaRepository;
	private final FacturaDetalleRepository facturaDetalleRepository;


	@Autowired
	public FacturaService(ClienteRepository clienteRepository, ProductoRepository productoRepository,
			VendedorRepository vendedorRepository, FacturaRepository facturaRepository,
			FacturaDetalleRepository facturaDetalleRepository) {
		this.clienteRepository = clienteRepository;
		this.productoRepository = productoRepository;
		this.vendedorRepository = vendedorRepository;
		this.facturaRepository = facturaRepository;
		this.facturaDetalleRepository = facturaDetalleRepository;
	}

	public Factura procesarCompra(Compra compra) {	

		try {
			Factura factura = new Factura();
			factura.setIdCliente(compra.getIdCliente());
			factura.setIdVendedor(compra.getIdVendedor());
			factura.setFecha(new java.sql.Date(compra.getFecha().getTime()));
			factura.setEstado(false);
			factura.setCodigo(compra.getCodigo());

			List<CompraDetalle> items = compra.getCompraDetalle();
			List<FacturaDetalle> listaFd = new ArrayList<FacturaDetalle>();		
			double total =0.00;

			for (CompraDetalle cd : items) {
				FacturaDetalle fd = new FacturaDetalle();
				fd.setIdProducto(cd.getIdProducto());
				fd.setCantidad(cd.getCantidad());			
				fd.setSubtotal(cd.getPrecio()*cd.getCantidad());
				//fd.setFechaA();
				listaFd.add(fd);
				total += fd.getSubtotal();
			}
			factura.setTotal(total);
			factura.setFacturaDetalle(listaFd);
			Factura _factura = this.facturaRepository.save(factura);	

			for (FacturaDetalle facturaDetalle : _factura.getFacturaDetalle()) 
				this.facturaDetalleRepository.save(new FacturaDetalle(0,facturaDetalle.getIdProducto(),facturaDetalle.getCantidad(),facturaDetalle.getSubtotal(),null,null, _factura));
			
			logger.trace("Compra Procesada ");
			logger.trace(_factura.toString());
			return _factura;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
			// TODO: handle exception
		}
	}

	public Factura aprobarCompra(long id) {	
		try {
			Optional<Factura> facturaInfo = this.facturaRepository.findById(id);
			if (facturaInfo.isPresent()) {
				Factura _factura = facturaInfo.get();
				_factura.setEstado(true);
				if(this.facturaRepository.save(_factura)!=null) {
					logger.trace("Compra Aprobada ");
					logger.trace(_factura.toString());
					return _factura;					
				}					
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
		}
		return null;
	}
}
