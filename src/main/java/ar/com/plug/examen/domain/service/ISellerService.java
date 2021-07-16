package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

public interface ISellerService {
    Page<Seller> findAll(Pageable pageable);
    Seller save(@Valid Seller seller);
    Seller getById(Long id);
    Seller update(@Valid Seller seller);
}
