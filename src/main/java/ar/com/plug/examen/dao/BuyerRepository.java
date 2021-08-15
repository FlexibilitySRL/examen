package ar.com.plug.examen.dao;

import ar.com.plug.examen.domain.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * System:                 FlexiTest
 * Name:                   BuyerRepository
 * Description:            Interface that handles access to the DB for the Buyer entity
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@Repository
public interface BuyerRepository extends JpaRepository<Buyer,Long>
{}