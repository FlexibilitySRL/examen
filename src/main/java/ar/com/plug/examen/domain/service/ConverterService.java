package ar.com.plug.examen.domain.service;

import java.util.List;

public interface ConverterService {

	<F, T> T convert(F from, Class<T> to);
	
	<F, T> List<T> convertList(List<F> list, Class<T> to);

}