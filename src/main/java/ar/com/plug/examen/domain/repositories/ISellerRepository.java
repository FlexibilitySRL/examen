package ar.com.plug.examen.domain.repositories;

import ar.com.plug.examen.domain.model.Seller;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ISellerRepository extends PagingAndSortingRepository<Seller,Long> {
}
