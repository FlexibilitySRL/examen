package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Sale;
import ar.com.plug.examen.domain.repository.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SaleService extends GenericService<Sale>{

    private static final Logger LOGGER =  LoggerFactory.getLogger(SaleService.class);
    private final SaleRepository repository;
    public SaleService(SaleRepository saleRepository) {
        this.repository = saleRepository;
    }

    @Override
    public SaleRepository getRepository() {
        return repository;
    }
}
