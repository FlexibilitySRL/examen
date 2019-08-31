package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.PurchaseApi;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.model.PurchaseItem;
import ar.com.flexibility.examen.domain.service.ClientService;
import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.domain.service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/rest")
public class PurchaseController {

    private final Logger log = LoggerFactory.getLogger(PurchaseController.class);

    private PurchaseService purchaseService;
    private ClientService  clientService;
    private ProductService productService;

    public PurchaseController(PurchaseService purchaseService,ClientService clientService,
                              ProductService productService){

        this.purchaseService = purchaseService;
        this.clientService = clientService;
        this.productService = productService;
    }

    /**
     * GET /purchases/id obtiene la compra indicada por id
     * @param id de compra a buscar
     * @return ResponseEntity con status 200 si la compra existe o 404 si no
     */
    @GetMapping("/purchases/{id}")
    public ResponseEntity<?> getPurchase(@PathVariable Long id) {
        log.debug("Request para obtener compra : {}", id);

        Purchase purchase = purchaseService.findById(id);

        if (purchase == null){
            log.debug("no existe id: {}", id);
            return new ResponseEntity<String>("La compra no existe", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<PurchaseApi>(toApi(purchase), HttpStatus.OK);
    }

    /**
     * DELETE  /purchases/id borra la compra indicada por id.
     * @param id de la compra a borrar
     * @return ResponseEntity with status 204
     */
    @DeleteMapping("/purchases/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable Long id) {
        log.debug("Request para borrar compra: {}", id);
        purchaseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * POST /purchases crea compra nueva
     * @param purchaseApi
     * @return ResponseEntity con status 201
     * @throws URISyntaxException
     */
    @PostMapping("/purchases")
    public ResponseEntity<?> createPurchase(@Valid @RequestBody PurchaseApi purchaseApi)throws URISyntaxException{
        log.debug("Request para crear compra : {}", purchaseApi);

        Client client = clientService.findById(purchaseApi.getClientId());

        if (client == null){
            log.error("el cliente no existe");
            return new ResponseEntity<String>("El cliente no existe", HttpStatus.NOT_FOUND);
        }

        Product product = productService.findById(purchaseApi.getProductId());

        if (product == null){
            log.error("el producto no existe");
            return new ResponseEntity<String>("El producto no existe", HttpStatus.NOT_FOUND);
        }

        if (!product.hasStock(purchaseApi.getUnits())){
            log.error("el producto no tiene suficiente stock");
            return new ResponseEntity<String>("El producto no tiene suficiente stock", HttpStatus.BAD_REQUEST);
        }

        Purchase result = purchaseService.create(toEntity(purchaseApi,client,product));

        if (result == null){
            log.error("no se pudo realizar la compra");
            return new ResponseEntity<String>("no se pudo realizar la compra", HttpStatus.BAD_REQUEST);
        }

        updateProduct(product,purchaseApi);

        return ResponseEntity.created(new URI("/rest/purchases/" + result.getId()))
                .body(toApi(result));
    }

    private void updateProduct(Product product, PurchaseApi purchaseApi) {
        product.setStock(product.getStock()-purchaseApi.getUnits());
        productService.update(product);

    }

    /**
     * PUT  /purchases actualiza una compra existente
     *
     * @param purchaseApi la compra a actualizar
     * @return ResponseEntity con status 200 (OK) o
     *         con status 404 si la compra no existe
     */
    @PutMapping("/purchases")
    public ResponseEntity<?> updatePurchase(@Valid @RequestBody PurchaseApi purchaseApi)throws URISyntaxException {
        log.debug("Request para actualizar compra : {}", purchaseApi);

        if (purchaseApi.getId() == null) {
            log.error("la compra no existe");
            return new ResponseEntity<String>("la compra no existe", HttpStatus.NOT_FOUND);
        }

        Purchase result = purchaseService.update(toUpdate(purchaseApi));

        if (result == null){
            log.error("la compra no existe");
            return new ResponseEntity<String>("la compra no existe", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok().body(toApi(result));
    }

    /**
     * GET  /purchases obtiene todas las compras
     * @return ResponseEntity con status 200
     */
    @GetMapping("/purchases")
    public ResponseEntity<List<PurchaseApi>> getAllPurchases(){
        log.info("Request para obtener todas las compras");
        List<Purchase> purchases = purchaseService.findAll();
        List<PurchaseApi> list = new ArrayList<>();
        for (Purchase purchase : purchases) {
            list.add(toApi(purchase));
        }

        return new ResponseEntity<List<PurchaseApi>>(list, HttpStatus.OK);
    }

    private Purchase toUpdate(PurchaseApi purchaseApi) {
        Purchase purchase = new Purchase();
        purchase.setId(purchaseApi.getId());
        return purchase;
    }

    private PurchaseApi toApi(Purchase purchase){
        PurchaseApi purchaseApi = new PurchaseApi();
        purchaseApi.setId(purchase.getId());
        purchaseApi.setClientId(purchase.getClientId());
        purchaseApi.setProductId(purchase.getProductId());
        purchaseApi.setUnits(purchase.getUnits());
        purchaseApi.setTotal(purchase.getTotal());
        purchaseApi.setSatus(purchase.getStatus());
        return purchaseApi;
    }

    private Purchase toEntity(PurchaseApi purchaseApi,Client client, Product product){
        PurchaseItem item = new PurchaseItem(product.getId(),purchaseApi.getUnits(),product.getPrice());
        Purchase purchase = new Purchase();
        purchase.setClient(client);
        purchase.setPurchaseItem(item);
        return purchase;
    }
}
