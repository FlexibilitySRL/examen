package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Approve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApproveRepository extends JpaRepository<Approve, Long> {
    Optional<Approve> findByTransactionId(String transactionId);
}
