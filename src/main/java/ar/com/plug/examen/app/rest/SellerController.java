package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.domain.exceptions.GenericBadRequestException;
import ar.com.plug.examen.domain.exceptions.GenericNotFoundException;
import ar.com.plug.examen.domain.service.SellerService;
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

@RestController
@RequestMapping(path = "/sellers")
public class SellerController {

  @Autowired
  private SellerService sellerService;

  /**
   * Get all sellers
   * @return List<SellerApi>
   */
  @GetMapping()
  public ResponseEntity<List<SellerApi>> getSellers() {
    return new ResponseEntity<>(this.sellerService.findAll(), HttpStatus.OK);
  }

  /**
   * Get seller by id
   * @param id
   * @return SellerApi
   * @throws GenericNotFoundException
   */
  @GetMapping("/findById/{id}")
  public ResponseEntity<SellerApi> getSellerById(@PathVariable("id") Long id) throws GenericNotFoundException {
    return new ResponseEntity<>(this.sellerService.findByIdChecked(id), HttpStatus.OK);
  }

  /**
   * Save a seller
   * @param sellerApi
   * @return SellerApi
   * @throws GenericBadRequestException
   */
  @PostMapping()
  public ResponseEntity<SellerApi> saveSeller(@RequestBody SellerApi sellerApi) throws GenericBadRequestException {
    return new ResponseEntity<>(this.sellerService.save(sellerApi), HttpStatus.CREATED);
  }

  /**
   * Update a seller
   * @param sellerApi
   * @return SellerApi
   * @throws GenericNotFoundException
   * @throws GenericBadRequestException
   */
  @PutMapping()
  public ResponseEntity<SellerApi> updateSeller(@RequestBody SellerApi sellerApi) throws GenericNotFoundException, GenericBadRequestException {
    return new ResponseEntity<>(this.sellerService.update(sellerApi), HttpStatus.OK);
  }

  /**
   * Delete a seller by id
   * @param id
   * @throws GenericNotFoundException
   */
  @DeleteMapping("/{id}")
  public ResponseEntity deleteSeller(@PathVariable("id") Long id) throws GenericNotFoundException {
    this.sellerService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
