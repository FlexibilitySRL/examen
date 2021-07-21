package ar.com.plug.examen.domain.repositories;

import ar.com.plug.examen.domain.model.Transac;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ITransacRepository extends PagingAndSortingRepository<Transac, Long> {

}
