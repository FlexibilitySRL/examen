package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.exceptions.GenericBadRequestException;
import ar.com.plug.examen.domain.exceptions.GenericNotFoundException;
import ar.com.plug.examen.domain.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController for product entity
 */
@RestController
@RequestMapping(path = "/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  /**
   * Get all products
   * @return List<ProductApi>
   */
  @GetMapping()
  public ResponseEntity<List<ProductApi>> getProducts() {
    return new ResponseEntity<>(this.productService.findAll(), HttpStatus.OK);
  }

  /**
   * Get product by id
   * @param id
   * @return ProductApi
   * @throws GenericNotFoundException
   */
  @GetMapping("/findById/{id}")
  public ResponseEntity<ProductApi> getProductById(@PathVariable("id") Long id) throws GenericNotFoundException {
    return new ResponseEntity<>(this.productService.findByIdChecked(id), HttpStatus.OK);
  }

  /**
   * Save a product
   * @param productApi
   * @return ProductApi
   * @throws GenericBadRequestException
   */
  @PostMapping()
  public ResponseEntity<ProductApi> saveProduct(@RequestBody ProductApi productApi) throws GenericBadRequestException {
    return new ResponseEntity<>(this.productService.save(productApi), HttpStatus.CREATED);
  }

  /**
   * Update a product
   * @param productApi
   * @return ProductApi
   * @throws GenericNotFoundException
   * @throws GenericBadRequestException
   */
  @PutMapping()
  public ResponseEntity<ProductApi> updateProduct(@RequestBody ProductApi productApi) throws GenericNotFoundException, GenericBadRequestException {
    return new ResponseEntity<>(this.productService.update(productApi), HttpStatus.OK);
  }

  /**
   * Delete a product by id
   * @param id
   * @throws GenericNotFoundException
   */
  @DeleteMapping("/{id}")
  public ResponseEntity deleteProduct(@PathVariable("id") Long id) throws GenericNotFoundException {
    this.productService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
