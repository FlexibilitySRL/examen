package ar.com.plug.examen.domain.model.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ClienteCompraRestDto extends ClienteRestDto implements Serializable {
;

	/**
	 * 
	 */
	private static final long serialVersionUID = -632976116649598880L;


	private List<CompraRestDto> compras;
	

}
