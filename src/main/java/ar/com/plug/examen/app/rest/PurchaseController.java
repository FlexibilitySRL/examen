package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.PurchaseDTO;
import ar.com.plug.examen.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping(path = "/savePurchase",  produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public void createProduct(@RequestBody PurchaseDTO purchaseDTO) {
        purchaseService.createPurchase(purchaseDTO);
    }

    @GetMapping(value = "/listPurchases", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PurchaseDTO> getListPurchase() {
      return purchaseService.listPurchase();
    }
}
