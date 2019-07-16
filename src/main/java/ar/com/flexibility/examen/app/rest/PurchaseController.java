package ar.com.flexibility.examen.app.rest;


import ar.com.flexibility.examen.app.exception.FlexibilityNotFoundException;
import ar.com.flexibility.examen.domain.service.PurchaseService;
import ar.com.flexibility.examen.domain.service.dto.PurchaseDTO;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Purchase API")
@RestController
@RequestMapping("/api")
public class PurchaseController extends BaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    /**
     * {@code POST  /purchases/:id/approve} : approve the "id" purchase.
     *
     * @param id the id of the purchaseDTO to approve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the purchaseDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/purchases/{id}/approve")
    public ResponseEntity<PurchaseDTO> approve(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseService.approve(id));
    }

    /**
     * {@code GET  /purchases} : get all the purchases.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of purchases in body.
     */
    @GetMapping("/purchases")
    public List<PurchaseDTO> findAll() {
        return purchaseService.findAll();
    }

    /**
     * {@code GET  /products/:id} : get the "id" product.
     *
     * @param id the id of the productDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/purchases/{id}")
    public ResponseEntity<PurchaseDTO> findById(@PathVariable Long id) {

        return purchaseService.findOne(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new FlexibilityNotFoundException("Id not found"));
    }
}
