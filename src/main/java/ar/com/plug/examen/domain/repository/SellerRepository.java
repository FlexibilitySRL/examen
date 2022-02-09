package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    public Seller findByNumberId(String numberID);
}
