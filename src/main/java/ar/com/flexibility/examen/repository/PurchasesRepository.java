package ar.com.flexibility.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.flexibility.examen.domain.model.Purchases;

@Repository
public interface PurchasesRepository extends JpaRepository<Purchases, String> {

}
