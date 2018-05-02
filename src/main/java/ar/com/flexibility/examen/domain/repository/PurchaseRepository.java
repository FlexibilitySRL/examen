package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Repository;

import ar.com.flexibility.examen.domain.model.Purchase;

@Repository
@EnableSpringDataWebSupport
public interface PurchaseRepository extends JpaRepository<Purchase, Long> , JpaSpecificationExecutor<Purchase>{

}
