package com.example.core.repository;

import com.example.core.model.Sale;
import com.example.core.model.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("select d from Sale s join s.client c, SaleDetail d where s.id = d.sale.id AND c.id = :idClient")
    public List<SaleDetail> findProductsByIdClient(@Param("idClient") Long idClient);

    @Query("select s from Sale s where s.id = :idSale AND s.bought = 0")
    public Sale findProductByIdSale(@Param("idSale") Long idSale);

    @Query("select d from Sale s, SaleDetail d where d.sale.id = s.id AND s.id = :idSale AND d.id = :idDetail")
    public SaleDetail findProductByIdSaleAndIdDetail(@Param("idSale") Long idSale, @Param("idDetail") Long idDetail);

    @Query("select d from Sale s, SaleDetail d where s.id = d.sale.id AND s.id = :idSale AND d.product.id = :idProduct")
    public SaleDetail findProductByIdSaleAndIdProduct(@Param("idSale") Long idSale, @Param("idProduct") Long idProduct);

    @Modifying
    @Query("update Sale set active = :active where id = :id")
    public void update(@Param("id") Long id,@Param("active") Boolean active);

    @Modifying
    @Query(value = "update SaleDetail set active = :active where id = :idDetail AND idSale = :idSale", nativeQuery = true)
    public void updateCart(@Param("idSale") Long id, @Param("idDetail") Long idDetail, @Param("active") Boolean active);
}
