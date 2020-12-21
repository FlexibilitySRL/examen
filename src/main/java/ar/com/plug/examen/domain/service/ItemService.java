package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.model.Item;

public interface ItemService {
	public abstract Item getOne(long id); 
	public abstract List<Item> getAll();
	public abstract Item add(Item item);
}
