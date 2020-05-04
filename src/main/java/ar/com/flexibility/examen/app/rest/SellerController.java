package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.dto.OrderApprovalDTO;
import ar.com.flexibility.examen.domain.model.Order;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.service.OrderService;
import ar.com.flexibility.examen.domain.service.SellerService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *  The ClientController class exposes the CRUD operations for the client model,
 *  additionally it exposes the orders management actions for a particular seller.
 *
 * @author  Amador Cuenca <sphi02ac@gmail.com>
 * @version 1.0
 */
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
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully.")
    })
    public ResponseEntity<List<Seller>> getAllSellers() {
        List<Seller> sellers = sellerService.retrieveSellers();

        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully."),
            @ApiResponse(code = 404, message = "Could not retrieve the resource.")
    })
    public ResponseEntity<Seller> getSellerById(@PathVariable("id") Long id) {
        Seller seller = sellerService.retrieveSellerById(id);

        if (seller == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource was created successfully."),
            @ApiResponse(code = 400, message = "Could not update the resource.")
    })
    public ResponseEntity<Seller> createSeller(@Valid @NotNull @RequestBody Seller seller) {
        Seller newSeller = sellerService.addSeller(seller);

        if (newSeller == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(newSeller, HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully."),
            @ApiResponse(code = 500, message = "Could not delete the resource.")
    })
    public ResponseEntity<Seller> updateSeller(@PathVariable("id") Long id,
                                               @Valid @NotNull @RequestBody Seller seller) {
        Seller updatedSeller = sellerService.updateSeller(id, seller);

        if (updatedSeller == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(path = "{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully."),
            @ApiResponse(code = 500, message = "Could not delete the resource.")
    })
    public ResponseEntity deleteSeller(@PathVariable("id") Long id) {
        boolean deletedSeller = sellerService.deleteSeller(id);

        if (deletedSeller) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "{id}/orders")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully."),
            @ApiResponse(code = 404, message = "Could not retrieve the resource.")
    })
    public ResponseEntity<List<Order>> getOrdersBySeller(@PathVariable("id") Long id) {
        Seller seller = sellerService.retrieveSellerById(id);

        if (seller == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Order> orders = orderService.retrieveOrderBySeller(seller);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PatchMapping(path = "{id}/orders")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully."),
            @ApiResponse(code = 400, message = "Could not create the resource."),
            @ApiResponse(code = 404, message = "Could not retrieve parent the resource."),
            @ApiResponse(code = 500, message = "Could not add the resource."),
    })
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
