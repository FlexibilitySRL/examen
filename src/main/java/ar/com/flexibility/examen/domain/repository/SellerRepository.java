package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.flexibility.examen.domain.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long>{
}
