package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.service.dto.PurchaseDTO;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {

    Optional<PurchaseDTO> findOne(Long id);

    List<PurchaseDTO> findAll();

    PurchaseDTO approve(Long id);

}
