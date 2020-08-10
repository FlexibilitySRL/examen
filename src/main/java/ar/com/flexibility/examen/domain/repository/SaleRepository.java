package ar.com.flexibility.examen.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.flexibility.examen.domain.enu.SaleStatus;
import ar.com.flexibility.examen.domain.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	Sale getFirstByCode(String code);

	List<Sale> findByStatus(SaleStatus saleStatus);

}
