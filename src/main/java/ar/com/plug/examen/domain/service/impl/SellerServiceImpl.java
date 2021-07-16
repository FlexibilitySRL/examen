package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repositories.ISellerRepository;
import ar.com.plug.examen.domain.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class SellerServiceImpl implements ISellerService {
    @Autowired
    private ISellerRepository repository;

    @Override
    public Page<Seller> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Seller save(@Valid Seller seller) {
        return repository.save(seller);
    }

    @Override
    public Seller getById(Long id) {
        if (repository.existsById(id))
            return repository.findById(id).get();

        return null;
    }

    @Override
    public Seller update(@Valid Seller seller) {
        if (repository.existsById(seller.getId())){
            return repository.save(seller);
        }

        return null;
    }
}
