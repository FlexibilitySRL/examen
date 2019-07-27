package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.ShoppingList;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingListRepository extends CrudRepository<ShoppingList, Long> {
}
