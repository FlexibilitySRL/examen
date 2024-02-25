package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.Sale;
import ar.com.plug.examen.domain.service.impl.SaleServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/sales")
public class SaleController {

    private final SaleServiceImpl saleService;

    public SaleController(SaleServiceImpl saleService) {
        this.saleService = saleService;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<Sale>> getAll(){
        List<Sale> sales = this.saleService.getAll();
        return ResponseEntity.ok(sales);
    }


}
