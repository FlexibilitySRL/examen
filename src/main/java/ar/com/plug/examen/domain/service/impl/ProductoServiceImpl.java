package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.dto.ProductoRestDto;
import ar.com.plug.examen.domain.model.entity.Producto;
import ar.com.plug.examen.domain.model.repository.ProductoRepository;
import ar.com.plug.examen.domain.service.IProductoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	public ProductoRestDto updateProducto(ProductoRestDto productoRestDto) {
		
		Producto producto = mapProductoRestDto(productoRestDto);
		
		Producto aux = productoRepository.save(producto);
		
		return mapProducto(aux);
	}

	@Override
	public  List<ProductoRestDto> getProductos (){
		
		List<ProductoRestDto> productoRestDtos = new ArrayList<ProductoRestDto>();
		
		productoRepository.findAll()
			.forEach(producto -> productoRestDtos.add(ProductoServiceImpl.mapProducto(producto)));
		
		return productoRestDtos;
		
	}
	
	@Override
	public ProductoRestDto getProductoById(Long idProducto) {
		
		Optional<Producto> opProducto = productoRepository.findById(idProducto);

		return opProducto.isPresent() ? mapProducto(opProducto.get()) : null;
				
	}


	public static ProductoRestDto mapProducto(Producto producto) {
		
		ProductoRestDto productoRestDto = new ProductoRestDto();
		productoRestDto.setId(producto.getId());
		productoRestDto.setNombreProducto(producto.getNombreProducto());
		productoRestDto.setPrecio(producto.getPrecio());
		return productoRestDto;
		
	}

	public static Producto mapProductoRestDto (ProductoRestDto productoRestDto) {
		
		Producto producto = new Producto();
		producto.setId(productoRestDto.getId());
		producto.setNombreProducto(productoRestDto.getNombreProducto());
		producto.setPrecio(productoRestDto.getPrecio());
		return producto;
		
	}

	@Override
	public Boolean removeProductoById(Long id) {

		productoRepository.deleteById(id);
		
		return true;
		
	}

	
	
}
