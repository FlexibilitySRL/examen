package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Sale;
import ar.com.plug.examen.domain.repository.SaleRepository;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl extends GenericServiceImpl<Sale>{

    public SaleServiceImpl(SaleRepository saleRepository) {
        this.repository = saleRepository;
    }

}
