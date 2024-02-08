package ar.com.plug.examen.app.rest;


import ar.com.plug.examen.app.DTO.PurchaseDTO;
import ar.com.plug.examen.domain.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    /**
     * Obtiene todas las compras según el cliente.
     */
    @GetMapping
    public ResponseEntity<List<PurchaseDTO>> getPurchases(@RequestParam(required = false) UUID customerId, @RequestParam(required = false) UUID vendorId) {
        List<PurchaseDTO> purchases = purchaseService.getPurchasesByCriteria(customerId, vendorId);
        log.info("Consulta exitosa de todas las compras pertenecientes a {}", customerId);
        return ResponseEntity.ok(purchases);
    }

    /**
     * Aprueba una compra.
     */
    @PostMapping("/{id}/approve")
    public ResponseEntity<PurchaseDTO> approvePurchase(@PathVariable UUID id, @RequestBody UUID vendorId) {
        PurchaseDTO purchaseDTO = purchaseService.approvePurchase(id, vendorId);
        log.info("Compra aprobada con éxito: {}", purchaseDTO.id());
        return ResponseEntity.ok(purchaseDTO);
    }
}
