package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.repository.CrudRepository;

import ar.com.flexibility.examen.domain.model.InvoiceDetail;

public interface InvoiceDetailRepository extends CrudRepository<InvoiceDetail, Long> {

}