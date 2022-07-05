package ar.com.plug.examen.app.rest.impl;

import ar.com.plug.examen.app.api.PurchaseDTO;
import ar.com.plug.examen.app.dtoResponse.ListPurchaseResponseDTO;
import ar.com.plug.examen.app.dtoResponse.PurchaseResponseDTO;
import ar.com.plug.examen.app.rest.IPurchaseController;
import ar.com.plug.examen.domain.service.IPurchaseRequestService;

import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PurchaseController implements IPurchaseController {

    Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private IPurchaseRequestService purchaseRequestService;

    public ResponseEntity<PurchaseResponseDTO> addPurchaseRequest(@ApiParam(name = "Purchase_request", value = "New Purchase request", required = true) @RequestBody @Valid PurchaseDTO request) throws Exception {
        log.info("/add_purchase - POST - Body Request: " + request.toString());
        PurchaseResponseDTO dto = purchaseRequestService.create(request);
        log.info("/add_purchase - POST - Body Response: " + dto.toString());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public ResponseEntity<PurchaseResponseDTO> approvePurchaseRequest(@ApiParam(name = "Purchase_request", value = "Approve Purchase request", required = true) @PathVariable Long receiptId) throws Exception {
        log.info("/approve_purchase - PUT - Path Variable: " + receiptId);
        PurchaseResponseDTO dto = purchaseRequestService.approve(receiptId);
        log.info("/approve_purchase - PUT - Body Response: " + dto.toString());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public ResponseEntity<PurchaseResponseDTO> cancelPurchaseRequest(@ApiParam(name = "Purchase_request", value = "Cancel Purchase request", required = true) @PathVariable Long receiptId) throws Exception {
        log.info("/cancel_purchase - PUT - Path Variable: " + receiptId);
        PurchaseResponseDTO dto = purchaseRequestService.cancel(receiptId);
        log.info("/cancel_purchase - PUT - Body Response: " + dto.toString());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public ResponseEntity<ListPurchaseResponseDTO> listPurchaseRequest() throws Exception {
        log.info("/list_purchase - GET");
        ListPurchaseResponseDTO listDto = purchaseRequestService.list();
        log.info("/list_purchase - GET - Body Response: " + listDto.toString());
        return new ResponseEntity<>(listDto, HttpStatus.OK);
    }
}
