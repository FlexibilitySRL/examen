package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.rest.dto.SaleDTO;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Sale;
import ar.com.plug.examen.domain.service.impl.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<Sale>> getAll(){
        List<Sale> sales = this.saleService.getAll();
        return ResponseEntity.ok(sales);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Sale> getById(@PathVariable Long id){
        Sale sale = this.saleService.getById(id);
        return ResponseEntity.ok(sale);
    }

    @PostMapping
    public ResponseEntity<Sale> insert(@RequestBody Sale sale){
        Sale newSale = this.saleService.saveOrUpdate(sale);
        return ResponseEntity.ok(newSale);
    }

    @PatchMapping
    public ResponseEntity<Sale> update(@RequestBody SaleDTO saleDTO){
        Sale sale = this.saleService.getById(saleDTO.getId());
        sale.setApproved(saleDTO.getApproved());
        Sale updatedSale = this.saleService.saveOrUpdate(sale);
        return ResponseEntity.ok(updatedSale);
    }


    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id){
        this.saleService.delete(id);
    }

}
