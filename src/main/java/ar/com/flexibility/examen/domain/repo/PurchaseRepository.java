package ar.com.flexibility.examen.domain.repo;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchase;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Long>{
    public List<Purchase> findByCustomerAndProduct(Customer customer, Product product);
}
