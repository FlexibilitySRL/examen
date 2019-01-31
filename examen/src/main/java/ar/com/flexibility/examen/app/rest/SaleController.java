package ar.com.flexibility.examen.app.rest;

import com.example.core.model.SaleDetail;
import com.example.core.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/carrito")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @RequestMapping(path = "/lista_deseos", method = RequestMethod.GET)
    public List<SaleDetail> getSales(@RequestParam("idClient") Long idClient) {
        return saleService.getListByIdClient(idClient);
    }

    @RequestMapping(path = "/confirmar_compra", method = RequestMethod.POST)
    public ResponseEntity confirm(@RequestBody Long idSale) {
        return saleService.confirmBuy(idSale);
    }

    @RequestMapping(path = "/agregar_producto", method = RequestMethod.POST)
    public ResponseEntity addProduct(@RequestBody Long idSale, @RequestBody List<SaleDetail> listProducts) {
        return saleService.addToCart(idSale, listProducts);
    }

    @RequestMapping(path = "/quitar_producto", method = RequestMethod.POST)
    public ResponseEntity deleteFromCart(@RequestBody Long idSale, @RequestBody Long idDetail) {
        return saleService.deleteFromCart(idSale, idDetail);
    }
}
