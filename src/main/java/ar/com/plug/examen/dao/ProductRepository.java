package ar.com.plug.examen.dao;

import ar.com.plug.examen.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * System:                 FlexiTest
 * Name:                   ProductRepository
 * Description:            Interface that handles access to the DB for the Product entity
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,Long>
{}