package ar.com.plug.examen.datasource.repo;

import ar.com.plug.examen.datasource.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PurchaseRepo extends JpaRepository<Purchase, Long> {

    List<Purchase> findAllByCreationDateTimeBetween(
            Date creationTimeStart,
            Date creationTimeEnd);
}