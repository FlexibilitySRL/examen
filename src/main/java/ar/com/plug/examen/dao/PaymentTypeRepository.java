package ar.com.plug.examen.dao;

import ar.com.plug.examen.domain.entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * System:                 FlexiTest
 * Name:                   PaymentTypeRepository
 * Description:            Interface that handles access to the DB for the PaymentType entity
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType,Long>
{}