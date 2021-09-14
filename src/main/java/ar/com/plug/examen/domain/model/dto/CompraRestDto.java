package ar.com.plug.examen.domain.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class CompraRestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4962009388382257057L;

	private Long idCliente;
	
	private Long id;
	
	private Date fecha;
	
	private Boolean aprobada;
		
	private List<ProductoRestDto> productoRestDtos ;

	public void addProductoRestDto(ProductoRestDto mapProducto) {
		if (productoRestDtos==null) {
			productoRestDtos = new ArrayList<ProductoRestDto>();
		}
		productoRestDtos.add(mapProducto);
		
	}

	
}
