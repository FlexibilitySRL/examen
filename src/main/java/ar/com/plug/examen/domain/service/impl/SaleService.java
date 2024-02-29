package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.exception.NotFoundException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Sale;
import ar.com.plug.examen.domain.repository.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class SaleService{

    private static final Logger LOGGER =  LoggerFactory.getLogger(SaleService.class);

    private final SaleRepository repository;
    public SaleService(SaleRepository saleRepository) {
        this.repository = saleRepository;
    }

    public Sale getById(Long id){
        LOGGER.info("SaleService.getById() id :{}",id);
        return repository.findById(id).orElseThrow( () -> new NotFoundException("Entity with id "+id+" not found"));
    }

    public Sale saveOrUpdate(Sale sale) {
        LOGGER.info("SaleService.getById() sale :{}",sale);
        return this.repository.save(sale);
    }

    public void delete(Long id) {
        LOGGER.info("SaleService.delete() id :{}",id);
        Sale sale = this.getById(id);
        this.repository.delete(sale);
    }

    public List<Sale> getAll() {
        LOGGER.info("SaleService.getAll()");
        return this.repository.findAll();
    }



}
