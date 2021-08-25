package ar.com.plug.examen.domain.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.com.plug.examen.data.entity.Producto;
import ar.com.plug.examen.domain.dto.ProductoDTO;

@Component
public class ProductoMapper {

	public ProductoDTO mapProductoToProductoDTO(Producto producto) {

		ProductoDTO productoDTO =  new ProductoDTO(producto.getIdProducto(),producto.getCodigo(),producto.getDescripcion(), 
				producto.getCantidad(),producto.getPrecio());

		return productoDTO;
	}

	public Producto mapProductoDTOToProducto(ProductoDTO productoDTO)  {

		Producto Producto =  new Producto(productoDTO.getIdProducto(),productoDTO.getCodigo(),productoDTO.getDescripcion(), 
				productoDTO.getCantidad(),productoDTO.getPrecio());

		return Producto;
	}

	public List<ProductoDTO> mapListProductoToProductoDTO(List<Producto> productos){	
		List<ProductoDTO> listaProductoDTO = new ArrayList<ProductoDTO>();

		for (Producto producto : productos) {
			ProductoDTO productoDTO = mapProductoToProductoDTO(producto);
			listaProductoDTO.add(productoDTO);			
		}

		return listaProductoDTO;		
	}
}
