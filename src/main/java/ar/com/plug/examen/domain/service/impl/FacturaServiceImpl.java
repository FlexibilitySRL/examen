package ar.com.plug.examen.domain.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.data.entity.Factura;
import ar.com.plug.examen.data.entity.FacturaDetalle;
import ar.com.plug.examen.data.repository.FacturaDetalleRepository;
import ar.com.plug.examen.data.repository.FacturaRepository;
import ar.com.plug.examen.domain.dto.FacturaDTO;
import ar.com.plug.examen.domain.dto.FacturaDetalleDTO;
import ar.com.plug.examen.domain.exception.ResourceAlreadyExists;
import ar.com.plug.examen.domain.exception.ResourceNotFoundException;
import ar.com.plug.examen.domain.mapper.FacturaMapper;
import ar.com.plug.examen.domain.service.FacturaService;

@Service
public class FacturaServiceImpl implements FacturaService {

	public static final Logger logger = Logger.getLogger(FacturaService.class);

	@Autowired
	FacturaMapper facturaMapper;

	@Autowired
	FacturaRepository facturaRepository;

	@Autowired
	FacturaDetalleRepository facturaDetalleRepository;

	@Override
	public FacturaDTO crearFactura(FacturaDTO facturaDTO) {

		Optional<Factura> facturaInfo = this.facturaRepository.findFacturaByCodigo(facturaDTO.getCodigo());

		if(facturaInfo.isPresent()) {
			throw new ResourceAlreadyExists("Invoice exist");
		}

		Factura factura = this.facturaMapper.mapFacturaDTOToFactura(facturaDTO);
		Factura factura_ = this.facturaRepository.save(factura);	

		List<FacturaDetalle> listaFacturaDetalle = this.facturaMapper.mapListaFacturaDetalleDTOToListaFacturaDetalle(facturaDTO.getFacturaDetalle());
		List<FacturaDetalle> listaFacturaDetalle_ = new ArrayList<>();

		for (FacturaDetalle facturaDetalle : listaFacturaDetalle) {
			FacturaDetalle facturaDetalle_ = this.facturaDetalleRepository.save(new FacturaDetalle(0,facturaDetalle.getIdProducto(),facturaDetalle.getCantidad(),facturaDetalle.getSubtotal(), factura_));
			listaFacturaDetalle_.add(facturaDetalle_);
		}		

		List<FacturaDetalleDTO> listaFacturaDetalleDTO = this.facturaMapper.mapListaFacturaDetalleToListaFacturaDetalleDTO(listaFacturaDetalle_);

		FacturaDTO facturaDTO_  = this.facturaMapper.mapFacturaToFacturaDTO(factura_);
		facturaDTO_.setFacturaDetalle(listaFacturaDetalleDTO);
		logger.trace("Factura Registrada "+ facturaDTO_.toString());

		return  facturaDTO_;

	}

	@Override
	public FacturaDTO aprobarFactura(long id) {
		Optional<Factura> facturaInfo = this.facturaRepository.findById(id);
		if (facturaInfo.isPresent()) {	
			facturaInfo.get().setEstado(true);
			return this.facturaMapper.mapFacturaToFacturaDTO(facturaRepository.save(facturaInfo.get()));
		}else {
			throw new ResourceNotFoundException("Invalid id");
		}
	}

	@Override
	public List<FacturaDTO> listarFacturas() {
		List<FacturaDTO> listaFacturaDTO = new ArrayList<>(); 
		List<Factura> listaFactura = this.facturaRepository.findAll();

		for (Factura factura : listaFactura) {
			FacturaDTO facturaDTO = this.facturaMapper.mapFacturaToFacturaDTO(factura);
			List<FacturaDetalleDTO> listaFacturaDetalleDTO= this.facturaMapper.mapListaFacturaDetalleToListaFacturaDetalleDTO(factura.getFacturaDetalle());
			facturaDTO.setFacturaDetalle(listaFacturaDetalleDTO);
			listaFacturaDTO.add(facturaDTO);
		}

		return listaFacturaDTO;
	}
}
