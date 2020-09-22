package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.flexibility.examen.domain.model.Purchases;

public interface PurchasesRepository  extends JpaRepository<Purchases,Long> {

}
