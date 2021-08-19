package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.dto.Product;
import ar.com.plug.examen.domain.dto.Sale;
import ar.com.plug.examen.objects.JsonRequestSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

import static ar.com.plug.examen.utils.QueryUtils.QUERY_COUNT_ROWS_SALE;
import static ar.com.plug.examen.utils.QueryUtils.QUERY_LAST_ID_SALE;


public interface SaleRepository extends JpaRepository<Sale, Serializable> {
    @Query(value=QUERY_LAST_ID_SALE, nativeQuery = true)
    Long findTopByOrderByIdDesc();
    @Query(value=QUERY_COUNT_ROWS_SALE, nativeQuery = true)
    Integer returnCount();
    Sale findById(Long id);
    List<Sale> findAll();
}
