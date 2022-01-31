package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<Client, Long>
{
}
