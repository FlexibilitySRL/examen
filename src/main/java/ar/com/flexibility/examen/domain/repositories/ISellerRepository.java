package ar.com.flexibility.examen.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.flexibility.examen.domain.model.Seller;

public interface ISellerRepository extends JpaRepository<Seller, Long> {
	
}
