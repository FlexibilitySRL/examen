package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.PaymentTypeDTO;

import java.util.List;

/**
 * System:                  FlexiTest
 * Name:                    PaymentTypeService
 * Description:             Interface for handling service layer of PaymentType's Entity
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public interface PaymentTypeService
{
    List<PaymentTypeDTO> findAll();
}
