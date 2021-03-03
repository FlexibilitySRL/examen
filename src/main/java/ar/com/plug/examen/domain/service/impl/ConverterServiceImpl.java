package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.service.ConverterService;

@Service
public class ConverterServiceImpl implements ConverterService{
	private final ModelMapper modelMapper;

	@Autowired
	public ConverterServiceImpl(Set<Converter<?, ?>> converters) {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
			.setSourceNameTokenizer(NameTokenizers.CAMEL_CASE)
			.setDestinationNameTokenizer(NameTokenizers.CAMEL_CASE);
		converters.forEach( (converter) -> modelMapper.addConverter(converter));
	}

	@Override
	public <F, T> T convert(F from, Class<T> to) {
		return modelMapper.map(from, to);
	}

	@Override
	public <F, T> List<T> convertList(List<F> list, Class<T> to) {
		return list.stream().map(item -> modelMapper.map(item, to)).collect(Collectors.toList());
	}
}
