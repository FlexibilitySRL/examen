package ar.com.plug.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.Client;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long>, JpaSpecificationExecutor<Client> {

}
