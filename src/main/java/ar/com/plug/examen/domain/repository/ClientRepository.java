package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    public Client findByNumberID(String numberID);
    public List<Client> findByLastName(String lastName);
    public Client findByEmail(String email);
}
