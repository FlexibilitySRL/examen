package ar.com.plug.examen.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.app.rest.paths.Paths;
import ar.com.plug.examen.domain.Exeptions.BadRequestException;
import ar.com.plug.examen.domain.Exeptions.NotFoundException;
import ar.com.plug.examen.domain.service.SellerService;

@RestController
@RequestMapping(path= Paths.SELLER)
public class SellerController {
	@Autowired
    private SellerService sellerService;

    @GetMapping()
    public ResponseEntity<List<SellerApi>> listSellers() {
        return new ResponseEntity<>(sellerService.listAll(), HttpStatus.OK);
    }

    @GetMapping(Paths.FIND_BY_ID)
    public ResponseEntity<SellerApi> findById(@PathVariable long id) throws NotFoundException {
    	return new ResponseEntity<>(sellerService.findById(id), HttpStatus.OK);
    }

    @GetMapping(Paths.FIND_BY_NAME)
    public ResponseEntity<List<SellerApi>> findByName(@PathVariable String name) {
    	return new ResponseEntity<>(sellerService.findByName(name), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<SellerApi> save(@RequestBody SellerApi seller) throws BadRequestException {
    	return new ResponseEntity<>(sellerService.save(seller), HttpStatus.CREATED);
    }

	@DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) throws NotFoundException {
        sellerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SellerApi> update(@RequestBody SellerApi sellerApi) throws NotFoundException, BadRequestException {
    	return new ResponseEntity<>(sellerService.update(sellerApi), HttpStatus.ACCEPTED);
    }
}
