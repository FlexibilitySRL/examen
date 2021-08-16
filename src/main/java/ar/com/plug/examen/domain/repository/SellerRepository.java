package ar.com.plug.examen.domain.repository;
import ar.com.plug.examen.domain.dto.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

import static ar.com.plug.examen.utils.QueryUtils.QUERY_LAST_ID;
public interface SellerRepository extends JpaRepository<Seller,Serializable> {
    @Query(value=QUERY_LAST_ID, nativeQuery = true)
    Integer findTopByOrderByIdDesc();
    Seller findById(Integer id);
    Seller findByName(String name);
}
