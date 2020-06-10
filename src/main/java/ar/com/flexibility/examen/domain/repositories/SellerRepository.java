package ar.com.flexibility.examen.domain.repositories;

import ar.com.flexibility.examen.domain.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    Optional<Seller> findById(Long id);
}
