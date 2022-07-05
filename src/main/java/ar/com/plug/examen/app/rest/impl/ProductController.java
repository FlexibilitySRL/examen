package ar.com.plug.examen.app.rest.impl;

import ar.com.plug.examen.app.api.ProductDTO;
import ar.com.plug.examen.app.dtoResponse.ProductResponseDTO;
import ar.com.plug.examen.app.dtoResponse.ListProductResponseDTO;
import ar.com.plug.examen.app.rest.IProductController;
import ar.com.plug.examen.domain.service.IProductRequestService;

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
public class ProductController implements IProductController{

    Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private IProductRequestService productRequestService;

    public ResponseEntity<ProductResponseDTO> addProductRequest(@ApiParam(name = "Product_request", value = "New Product request", required = true) @RequestBody @Valid ProductDTO request) throws Exception {
        log.info("/add_product - POST - Body Request: " + request.toString());
        ProductResponseDTO dto = productRequestService.create(request);
        log.info("/add_client - POST - Body Response: " + dto.toString());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public ResponseEntity<ProductResponseDTO> updateProductRequest(@ApiParam(name = "Product_request", value = "Update Product request", required = true) @RequestBody @Valid ProductDTO request) throws Exception {
        log.info("/update_product - PUT - Body Request: " + request.toString());
        ProductResponseDTO dto = productRequestService.update(request);
        log.info("/update_product - PUT - Body Response: " + dto.toString());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public ResponseEntity<ProductResponseDTO> removeProductRequest(@ApiParam(name = "Product_request", value = "Remove Product request", required = true) @PathVariable String productCode) throws Exception {
        log.info("/remove_product - DELETE - Path Variable: " + productCode);
        ProductResponseDTO dto = productRequestService.delete(productCode);
        log.info("/remove_product - DELETE - Body Response: " + dto.toString());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public ResponseEntity<ListProductResponseDTO> listProductRequest() throws Exception {
        log.info("/list_product - GET");
        ListProductResponseDTO listDto = productRequestService.list();
        log.info("/list_product - GET - Body Response: " + listDto.toString());
        return new ResponseEntity<>(listDto, HttpStatus.OK);
    }
}
