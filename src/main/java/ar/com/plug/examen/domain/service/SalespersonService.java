package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.model.Salesperson;

public interface SalespersonService {
	public abstract Salesperson getOne(long id); 
	public abstract List<Salesperson> getAll();
	public abstract Salesperson add(Salesperson salesperson);
	public abstract Salesperson modify(Salesperson salesperson); 
	public abstract void delete (long id);
}
