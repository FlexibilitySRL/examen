package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    @Modifying
    @Transactional
    @Query("delete from Seller s where s.id = ?1")
    void deleteById(long id);
}
