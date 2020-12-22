package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.model.Operation;

public interface OperationService {
	public abstract Operation getOne(long id); 
	public abstract List<Operation> getAll();
	public abstract Operation add(Operation operation);
	public abstract Operation aprove(long id);
	// public abstract Operation modify(Operation operation); 
	// public abstract void delete (long id);
}
