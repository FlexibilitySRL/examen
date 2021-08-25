package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.data.entity.Producto;
import ar.com.plug.examen.data.repository.ProductoRepository;
import ar.com.plug.examen.domain.dto.ProductoDTO;
import ar.com.plug.examen.domain.exception.ResourceNotFoundException;
import ar.com.plug.examen.domain.mapper.ProductoMapper;
import ar.com.plug.examen.domain.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	public static final Logger logger = Logger.getLogger(ProductoService.class);

	private ProductoRepository produtoRepository;

	private ProductoMapper productoMapper;

	@Autowired
	private  ProductoServiceImpl(ProductoRepository produtoRepository,ProductoMapper productoMapper) {
		this.produtoRepository = produtoRepository;
		this.productoMapper = productoMapper;
	}

	@Override
	public List<ProductoDTO> listarProductos() {
		List<ProductoDTO>  listProductoDTO = this.productoMapper.mapListProductoToProductoDTO(this.produtoRepository.findAll());
		logger.trace("Se retorna(n) "+ listProductoDTO.size()+" registro(s)");
		return listProductoDTO;
	}

	@Override
	public ProductoDTO consultarProductoPorId(long id) {
		if(id <=0) {
			throw new RuntimeException();
		}
		Optional<Producto> productoInfo =  this.produtoRepository.findById(id); 
		if (productoInfo.isPresent()) {
			logger.trace("Registro Existe: " +productoInfo.get().toString());
			return this.productoMapper.mapProductoToProductoDTO(productoInfo.get());
		}else {
			throw new ResourceNotFoundException("Invalid id");
		}
	}

	@Override
	public ProductoDTO crearProducto(ProductoDTO productoDTO) {
		ProductoDTO productDTO =  this.productoMapper.mapProductoToProductoDTO(this.produtoRepository.save(this.productoMapper.mapProductoDTOToProducto(productoDTO)));
		logger.trace("Se crea el registro ID: " +productDTO.getIdProducto());
		return productDTO;
	}

	@Override
	public ProductoDTO actualizarProducto(ProductoDTO productoDTO) {
		Optional<Producto> productoInfo =  this.produtoRepository.findById(productoDTO.getIdProducto());
		if (productoInfo.isPresent()) {
			logger.trace("Registro actualizado: " +productoInfo.get().toString());
			return this.productoMapper.mapProductoToProductoDTO(this.produtoRepository.save(this.productoMapper.mapProductoDTOToProducto(productoDTO)));
		}else {
			throw new ResourceNotFoundException("Invalid id");
		}
	}

	@Override
	public void eliminarProducto(long id) {
		Optional<Producto> productoInfo =  this.produtoRepository.findById(id);
		if (productoInfo.isPresent()) {
			this.produtoRepository.deleteById(id);
			logger.trace("Registro eliminado: " +productoInfo.get().toString());
		}else {
			throw new ResourceNotFoundException("Invalid id");
		}
	}
}
