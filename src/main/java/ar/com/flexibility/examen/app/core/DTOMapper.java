package ar.com.flexibility.examen.app.core;

import org.modelmapper.ModelMapper;

public class DTOMapper {

	private static final ModelMapper modelMapper = new ModelMapper();

	private DTOMapper(){}

	public static ModelMapper getInstance() {
		return modelMapper;
	}
}
