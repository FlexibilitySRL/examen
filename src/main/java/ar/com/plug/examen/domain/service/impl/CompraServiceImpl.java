package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.dto.CompraRestDto;
import ar.com.plug.examen.domain.model.dto.ProductoRestDto;
import ar.com.plug.examen.domain.model.entity.Cliente;
import ar.com.plug.examen.domain.model.entity.Compra;
import ar.com.plug.examen.domain.model.entity.Producto;
import ar.com.plug.examen.domain.model.repository.ClienteRepository;
import ar.com.plug.examen.domain.model.repository.CompraRepository;
import ar.com.plug.examen.domain.model.repository.ProductoRepository;
import ar.com.plug.examen.domain.service.ICompraService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraServiceImpl implements ICompraService {

	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public CompraRestDto updateCompra(CompraRestDto compraRestDto) {
		
		Compra compra = mapCompraRestDto(compraRestDto);
		
		Optional<Cliente> opCliente = clienteRepository.findById(compraRestDto.getIdCliente());
		compra.setCliente(opCliente.isPresent() ? opCliente.get() : null);;
	
		
		for (ProductoRestDto producto : compraRestDto.getProductoRestDtos()) {
		
			Optional<Producto> opProducto = productoRepository.findById(producto.getId());
			compra.addProducto(opProducto.isPresent() ? opProducto.get() : null);
			
		}
		compra.setFecha(new Date());

		Compra aux = compraRepository.save(compra);
		
		return mapCompra(aux);
	}

	@Override
	public  List<CompraRestDto> getCompras (){
		
		List<CompraRestDto> compraRestDtos = new ArrayList<CompraRestDto>();
		
		compraRepository.findAll()
			.forEach(compra -> compraRestDtos.add(CompraServiceImpl.mapCompra(compra)));
		
		return compraRestDtos;
		
	}
	
	@Override
	public CompraRestDto getCompraById(Long idCompra) {
		
		Optional<Compra> opCompra = compraRepository.findById(idCompra);

		return opCompra.isPresent() ? mapCompra(opCompra.get()) : null;
				
	}


	private static CompraRestDto mapCompra(Compra compra) {
		
		CompraRestDto compraRestDto = new CompraRestDto();
		compraRestDto.setId(compra.getId());
		compraRestDto.setFecha(compra.getFecha());
		compraRestDto.setAprobada(compra.getAprobada());
		if (compra.getProductos()!=null && !compra.getProductos().isEmpty()) {
			for (Producto producto : compra.getProductos()) {
				compraRestDto.addProductoRestDto(ProductoServiceImpl.mapProducto(producto));
			}
		}
		return compraRestDto;
		
	}

	public static Compra mapCompraRestDto (CompraRestDto compraRestDto) {
		
		Compra compra = new Compra();
		compra.setId(compraRestDto.getId());
		compra.setFecha(compraRestDto.getFecha());
		compra.setAprobada(compraRestDto.getAprobada() ? true : false);
			

		return compra;
		
	}

	@Override
	public Boolean removeCompraById(Long id) {

		compraRepository.deleteById(id);
		
		return true;
		
	}

	public static List<CompraRestDto> mapComprasCompletas(List<Compra> compras) {
		
		List<CompraRestDto> compraRestDtos = new ArrayList<CompraRestDto>();
		
		
		compras.forEach(compra -> compraRestDtos.add(CompraServiceImpl.mapCompra(compra)));

				
		return compraRestDtos;
		
	}
	

	@Override
	public Boolean aprobarCompraById(Long id) {

		Optional<Compra> opCliente = compraRepository.findById(id);		

		Boolean respuesta = opCliente.isPresent();

		if (respuesta) {
			opCliente.get().setAprobada(true);
			compraRepository.save(opCliente.get());
		}
		return respuesta;
		
	}



	
	
}
