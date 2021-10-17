package ar.com.plug.examen.domain.crud;

import ar.com.plug.examen.domain.model.Seller;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SellerCrudRepository extends CrudRepository<Seller, Long> {

    Optional<List<Seller>> findByStateEquals(String state);
}
