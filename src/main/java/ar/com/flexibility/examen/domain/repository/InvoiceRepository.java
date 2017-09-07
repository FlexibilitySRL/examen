package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.repository.CrudRepository;

import ar.com.flexibility.examen.domain.model.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long>{

}
