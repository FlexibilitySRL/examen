package ar.com.plug.examen.dao;

import ar.com.plug.examen.domain.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * System:                 FlexiTest
 * Name:                   SellerRepository
 * Description:            Interface that handles access to the DB for the Seller entity
 *
 * @author teixbr
 * @version 1.0
 * @since 15/08/21
 */
@Repository
public interface SellerRepository extends JpaRepository<Seller,Long>
{}