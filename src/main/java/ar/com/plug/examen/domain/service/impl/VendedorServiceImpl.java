package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.data.entity.Vendedor;
import ar.com.plug.examen.data.repository.VendedorRepository;
import ar.com.plug.examen.domain.dto.VendedorDTO;
import ar.com.plug.examen.domain.exception.ResourceNotFoundException;
import ar.com.plug.examen.domain.mapper.VendedorMapper;
import ar.com.plug.examen.domain.service.VendedorService;

@Service
public class VendedorServiceImpl implements VendedorService {

	public static final Logger logger = Logger.getLogger(VendedorService.class);

	private VendedorRepository vendedorRepository;

	private VendedorMapper vendedorMapper;

	@Autowired
	private  VendedorServiceImpl(VendedorRepository vendedorRepository,VendedorMapper vendedorMapper) {
		this.vendedorRepository = vendedorRepository;
		this.vendedorMapper = vendedorMapper;
	}

	@Override
	public List<VendedorDTO> listarVendedores() {
		 List<VendedorDTO> listaVendedorDTO = this.vendedorMapper.mapListVendedorToVendedorDTO(this.vendedorRepository.findAll());
		 logger.trace("Se retorna(n) "+ listaVendedorDTO.size()+" registro(s)");
		 return listaVendedorDTO;
	}

	@Override
	public VendedorDTO consultarVendedorPorId(long id) {
		if(id <=0) {
			throw new RuntimeException();
		}
		Optional<Vendedor> vendedorInfo =  this.vendedorRepository.findById(id); 
		if (vendedorInfo.isPresent()) {
			logger.trace("Registro encontrado");
			return this.vendedorMapper.mapClienteToClienteDTO(vendedorInfo.get());
		}else {
			throw new ResourceNotFoundException("Invalid id");
		}
	}

	@Override
	public VendedorDTO crearVendedor(VendedorDTO vendedorDTO) {		
		VendedorDTO vendedorDTO_ = this.vendedorMapper.mapClienteToClienteDTO(this.vendedorRepository.save(this.vendedorMapper.mapClienteDTOToCliente(vendedorDTO)));
		logger.trace("Se crea el registro ID: " +vendedorDTO_.getId());
		return vendedorDTO_;
	}

	@Override
	public VendedorDTO actualizarVendedor(VendedorDTO vendedorDTO) {
		Optional<Vendedor> vendedorInfo =  this.vendedorRepository.findById(vendedorDTO.getId());
		if (vendedorInfo.isPresent()) {
			logger.trace("Registro actualizado: " +vendedorInfo.get().toString());
			return this.vendedorMapper.mapClienteToClienteDTO(this.vendedorRepository.save(this.vendedorMapper.mapClienteDTOToCliente(vendedorDTO)));
		}else {
			throw new ResourceNotFoundException("Invalid id");
		}
	}

	@Override
	public void eliminarVendedor(long id) {
		Optional<Vendedor> vendedorInfo =  this.vendedorRepository.findById(id);
		if (vendedorInfo.isPresent()) {
			this.vendedorRepository.deleteById(id);
			logger.trace("Registro encontrado y eliminado");
		}else {
			throw new ResourceNotFoundException("Invalid id");
		}
	}
}
