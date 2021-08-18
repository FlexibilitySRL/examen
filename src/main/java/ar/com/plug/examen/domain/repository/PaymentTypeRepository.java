package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.dto.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Serializable> {
}
