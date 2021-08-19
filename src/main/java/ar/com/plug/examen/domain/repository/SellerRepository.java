package ar.com.plug.examen.domain.repository;

import java.io.Serializable;

import ar.com.plug.examen.domain.dto.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SellerRepository extends JpaRepository<Seller, Serializable> {
    Seller findById(Long id);
}
