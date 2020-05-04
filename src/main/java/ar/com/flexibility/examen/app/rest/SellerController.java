package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.dto.OrderApprovalDTO;
import ar.com.flexibility.examen.domain.model.Order;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.service.OrderService;
import ar.com.flexibility.examen.domain.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/sellers")
public class SellerController {

    private SellerService sellerService;
    private OrderService orderService;

    @Autowired
    public SellerController(SellerService sellerService, OrderService orderService) {
        this.sellerService = sellerService;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers() {
        List<Seller> sellers = sellerService.retrieveSellers();

        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable("id") Long id) {
        Seller seller = sellerService.retrieveSellerById(id);

        if (seller == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Seller> createSeller(@Valid @NotNull @RequestBody Seller seller) {
        Seller newSeller = sellerService.addSeller(seller);

        if (newSeller == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(newSeller, HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable("id") Long id,
                                               @Valid @NotNull @RequestBody Seller seller) {
        Seller updatedSeller = sellerService.updateSeller(id, seller);

        if (updatedSeller == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "{id}/orders")
    public ResponseEntity<List<Order>> getOrdersBySeller(@PathVariable("id") Long id) {
        Seller seller = sellerService.retrieveSellerById(id);

        if (seller == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Order> orders = orderService.retrieveOrderBySeller(seller);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PatchMapping(path = "{id}/orders")
    public ResponseEntity<String> updateOrderStatus(@PathVariable("id") Long id,
                                                    @Valid @NotNull @RequestBody OrderApprovalDTO orderApprovalDTO) {
        Seller seller = sellerService.retrieveSellerById(id);

        if (seller == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Order order = orderService.retrieveOrderById(orderApprovalDTO.getOrderId());

        if (order == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean orderStatusUpdated = orderService.updateOrderStatus(order, orderApprovalDTO.isApproved());

        if (!orderStatusUpdated) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
