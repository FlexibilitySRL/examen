package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.SellerRepository;
import ar.com.flexibility.examen.domain.service.AbstractService;
import ar.com.flexibility.examen.domain.service.SellerService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultSellerService extends AbstractService<Seller, SellerRepository> implements SellerService {

    private static final Boolean NON_DELETED_FLAG = false;

    @Autowired
    public DefaultSellerService(SellerRepository repository) {
        super(repository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Seller> listAll() {
        return Lists.newArrayList(repository.findByDeleted(NON_DELETED_FLAG));
    }
}

