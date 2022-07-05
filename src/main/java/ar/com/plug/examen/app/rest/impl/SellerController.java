package ar.com.plug.examen.app.rest.impl;

import ar.com.plug.examen.app.api.SellerDTO;
import ar.com.plug.examen.app.dtoResponse.SellerResponseDTO;
import ar.com.plug.examen.app.dtoResponse.ListSellerResponseDTO;
import ar.com.plug.examen.app.rest.ISellerController;
import ar.com.plug.examen.domain.service.ISellerRequestService;
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
public class SellerController implements ISellerController {

    Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private ISellerRequestService sellerRequestService;

    public ResponseEntity<SellerResponseDTO> addSellerRequest(@ApiParam(name = "seller_request", value = "New seller request", required = true) @RequestBody @Valid SellerDTO request) throws Exception {
        log.info("/add_seller - POST - Body Request: " + request.toString());
        SellerResponseDTO dto = sellerRequestService.create(request);
        log.info("/add_seller - POST - Body Response: " + dto.toString());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public ResponseEntity<SellerResponseDTO> updateSellerRequest(@ApiParam(name = "seller_request", value = "Update seller request", required = true) @RequestBody @Valid SellerDTO request) throws Exception {
        log.info("/update_seller - PUT - Body Request: " + request.toString());
        SellerResponseDTO dto = sellerRequestService.update(request);
        log.info("/update_seller - PUT - Body Response: " + dto.toString());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public ResponseEntity<SellerResponseDTO> removeSellerRequest(@ApiParam(name = "seller_request", value = "Remove seller request", required = true) @PathVariable Integer document) throws Exception {
        log.info("/remove_seller - DELETE - Path Variable: " + document);
        SellerResponseDTO dto = sellerRequestService.delete(document);
        log.info("/remove_seller - DELETE - Body Response: " + dto.toString());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public ResponseEntity<ListSellerResponseDTO> listSellerRequest() throws Exception {
        log.info("/list_seller - GET");
        ListSellerResponseDTO listDto = sellerRequestService.list();
        log.info("/list_seller - GET - Body Response: " + listDto.toString());
        return new ResponseEntity<>(listDto, HttpStatus.OK);
    }
}
