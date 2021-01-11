package ar.com.plug.examen.domain.mappers;

import java.util.Collection;
import java.util.List;

public interface Mapper <E,D> {
	
	public D getDto(E entity);
	
	public E fillEntity(E entity, D dto);
	
	public List<D> getDto(Collection<E> entities);
	
}
