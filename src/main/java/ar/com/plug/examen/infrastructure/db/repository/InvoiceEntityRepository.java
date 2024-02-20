package ar.com.plug.examen.infrastructure.db.repository;

import org.springframework.data.repository.CrudRepository;

import ar.com.plug.examen.infrastructure.db.entity.InvoiceEntity;

public interface InvoiceEntityRepository extends CrudRepository<InvoiceEntity, String> {

}
