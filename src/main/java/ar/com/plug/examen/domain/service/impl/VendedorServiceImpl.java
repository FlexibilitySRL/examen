package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.dao.VendedorDao;
import ar.com.plug.examen.domain.dto.VendedorAltaDto;
import ar.com.plug.examen.domain.dto.VendedorUpdateDto;
import ar.com.plug.examen.domain.model.Vendedor;
import ar.com.plug.examen.domain.service.VendedorService;

@Service
public class VendedorServiceImpl implements VendedorService {
	
	@Autowired
	private VendedorDao dao;

	@Override
	public List<Vendedor> getSellers() {
		return dao.findAll();
	}

	@Override
	public Optional<Vendedor> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Vendedor createSeller(VendedorAltaDto source) {
		Vendedor entity = new Vendedor();
		entity.setApellido(source.getApellido());
		entity.setDireccion(source.getDireccion());
		entity.setDocumento(source.getDocumento());
		entity.setFechaNacimiento(source.getFechaNacimiento());
		entity.setNombre(source.getNombre());
		entity.setTelefono(source.getTelefono());
		return dao.save(entity);
	}

	@Override
	public Vendedor updateSeller(VendedorUpdateDto source) {
		Vendedor entity = findById(source.getId()).get();
		if (Optional.ofNullable(source.getApellido()).isPresent())
			entity.setApellido(source.getApellido());
		if (Optional.ofNullable(source.getDireccion()).isPresent())
			entity.setDireccion(source.getDireccion());
		if (Optional.ofNullable(source.getDocumento()).isPresent())
			entity.setDocumento(source.getDocumento());
		if (Optional.ofNullable(source.getFechaNacimiento()).isPresent())
			entity.setFechaNacimiento(source.getFechaNacimiento());
		if (Optional.ofNullable(source.getNombre()).isPresent())
			entity.setNombre(source.getNombre());
		if (Optional.ofNullable(source.getTelefono()).isPresent())
			entity.setTelefono(source.getTelefono());
		return dao.save(entity);
	}

	@Override
	public void deleteSeller(Integer id) {
		dao.deleteById(id);
	}

}
