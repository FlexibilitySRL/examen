package ar.com.plug.examen.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long>{

	Optional<Seller> findById(Long id);
	
}
