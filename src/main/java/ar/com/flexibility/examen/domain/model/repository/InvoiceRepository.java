package ar.com.flexibility.examen.domain.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.flexibility.examen.domain.model.db.Invoice;
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
