package ar.com.flexibility.examen.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.flexibility.examen.domain.model.Invoice;

/**
 * 
 * @author <a href="mailto:abeljose13@gmail.com">Avelardo Gavide</a>
 *
 */
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	
	List<Invoice> findAllByCustomerId(Long customerId);
}
