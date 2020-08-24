package ar.com.flexibility.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.flexibility.examen.domain.model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, String> {

	@Query(value = "SELECT * FROM SELLER WHERE ID = ?1", nativeQuery = true)
	Seller findById(Long id);

	@Query(value = "SELECT * FROM SELLER WHERE FIRST_NAME = ?1", nativeQuery = true)
	Seller findByName(String name);
}
