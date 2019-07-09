package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.entity.Vendedor;

public interface ProcessVendedorService {

	public List<Vendedor> findAll();

    public void save(Vendedor vendedor);

    public Vendedor findOne(Long id);

    public void delete(Long id);
}
