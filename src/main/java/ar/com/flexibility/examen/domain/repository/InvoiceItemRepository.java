package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.flexibility.examen.domain.model.InvoiceItem;

/**
 * 
 * @author <a href="mailto:abeljose13@gmail.com">Avelardo Gavide</a>
 *
 */
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {

}
