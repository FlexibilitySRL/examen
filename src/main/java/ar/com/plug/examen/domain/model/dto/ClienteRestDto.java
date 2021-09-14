package ar.com.plug.examen.domain.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClienteRestDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 314833844414346579L;

	private Long id;
		
	private String nombre;
	
	

	
}
