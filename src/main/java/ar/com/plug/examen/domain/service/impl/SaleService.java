package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Sale;
import ar.com.plug.examen.domain.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService{

    private final SaleRepository repository;
    public SaleService(SaleRepository saleRepository) {
        this.repository = saleRepository;
    }

    public Sale getById(Long id){
        return repository.findById(id).get();
    }

    public Sale saveOrUpdate(Sale sale) {
        return this.repository.save(sale);
    }

    public void delete(Long id) {
        Sale sale = this.getById(id);
        this.repository.delete(sale);
    }

    public List<Sale> getAll() {
        return this.repository.findAll();
    }

}
