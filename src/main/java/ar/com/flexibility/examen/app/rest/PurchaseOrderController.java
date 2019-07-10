package ar.com.flexibility.examen.app.rest;


import ar.com.flexibility.examen.app.api.request.PurchaseOrderEvaluationRequest;
import ar.com.flexibility.examen.app.api.request.PurchaseOrderRequest;
import ar.com.flexibility.examen.app.api.response.ApiError;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.PurchaseOrder;
import ar.com.flexibility.examen.domain.service.ClientService;
import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.domain.service.PurchaseService;
import ar.com.flexibility.examen.domain.service.exception.ClientNotFoundException;
import ar.com.flexibility.examen.domain.service.exception.ProductNotFoundException;
import ar.com.flexibility.examen.domain.service.exception.PurchaseOrderNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/purchase")
public class PurchaseOrderController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PurchaseService purchaseService;



    @GetMapping("{id}")
    public ResponseEntity<?> getPurchaseOrder (@PathVariable("id") Long purchaseOrderId) {

        PurchaseOrder purchaseOrder = null;
        try {
            purchaseOrder = purchaseService.getPurchaseOrder(purchaseOrderId);
        } catch (PurchaseOrderNotFound purchaseOrderNotFound) {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,
                    "Resource not found",
                    "Purchase Order not registered");
            return new ResponseEntity<>(apiError, apiError.getStatus());
        }

        return new ResponseEntity<>(purchaseOrder, HttpStatus.OK);
    }



    @PostMapping("")
    public ResponseEntity<?> createPurchaseOrder (@RequestBody PurchaseOrderRequest request) {

        // Consult Client
        Client client = null;
        try {
            client = clientService.getClientById(request.getClientId());
        } catch (ClientNotFoundException e) {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Resource not found", "Client not registered");
            return new ResponseEntity<>(apiError, apiError.getStatus());
        }

        // Consult Product
        Product product = null;
        try {
            product = productService.getProductById(request.getProductId());
        } catch (ProductNotFoundException e) {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Resource not found", "Product not registered");
            return new ResponseEntity<>(apiError, apiError.getStatus());
        }

        // Request Purchase Order
        PurchaseOrder purchaseOrder = purchaseService.requestPurchase(client, product, request.getAmount());

        return new ResponseEntity<>(purchaseOrder, HttpStatus.CREATED);
    }



    @PatchMapping("evaluate/{id}")
    public ResponseEntity<?> evaluatePurchaseOrder (@PathVariable("id") Long purchaseOrderId,
                                                   @RequestBody PurchaseOrderEvaluationRequest purchaseOrderEvaluationRequest) {

        PurchaseOrder purchaseOrder = null;

        try {
            purchaseOrder = purchaseService.getPurchaseOrder(purchaseOrderId);

            if (purchaseOrderEvaluationRequest.getStatus().toUpperCase().equals(PurchaseOrder.Status.ACCEPTED)) {
                purchaseOrder = purchaseService.aprovePurchase(purchaseOrder);
            } else if (purchaseOrderEvaluationRequest.getStatus().toUpperCase().equals(PurchaseOrder.Status.REVOKED)) {
                purchaseOrder = purchaseService.revokePurchase(purchaseOrder);
            } else {
                ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
                        "Wrong Status Data",
                        "Purchase Order State not recognized");
                return new ResponseEntity<>(apiError, apiError.getStatus());
            }
        } catch (PurchaseOrderNotFound purchaseOrderNotFound) {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,
                    "Resource not found",
                    "Purchase Order not registered");
            return new ResponseEntity<>(apiError, apiError.getStatus());
        }

        return new ResponseEntity<>(purchaseOrder, HttpStatus.OK);
    }

}
