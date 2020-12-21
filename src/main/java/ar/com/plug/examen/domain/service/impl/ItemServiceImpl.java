//package ar.com.plug.examen.domain.service.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import ar.com.plug.examen.domain.model.Item;
//import ar.com.plug.examen.domain.repository.ItemRepository;
//import ar.com.plug.examen.domain.service.ItemService;
//import ar.com.plug.examen.exception.ItemNotFoundException;
//
//@Service("ItemServiceImpl")
//public class ItemServiceImpl implements ItemService {
//	@Autowired
//	private ItemRepository itemRepository;
//	
//	
//	@Override
//	public Item getOne(long id) {
//		return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
//	}
//
//	@Override
//	public List<Item> getAll() {
//		return itemRepository.findAll();
//	}
//
//	@Override
//	public Item add(Item item) {
//		return itemRepository.save(item);
//	}
//}
