package ar.com.plug.examen.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;
import ar.com.plug.examen.domain.service.SellerService;

@RestController
@RequestMapping(path = Paths.SELLER)
public class SellerController {

	@Autowired
    private SellerService sellerService;

    @GetMapping()
    public List<SellerApi> listSellers() {
        return sellerService.listAll();
    }

    @GetMapping(Paths.FIND_BY_ID)
    public SellerApi findById(@PathVariable Long id) throws NotFoundException {
    	return sellerService.findById(id);
    }

    @GetMapping(Paths.FIND_BY_NAME)
    public List<SellerApi> findByName(@PathVariable String name) throws NotFoundException {
    	return sellerService.findByName(name);
    }
    
    @PostMapping()
    public SellerApi save(@RequestBody SellerApi seller) throws BadRequestException {
    	return sellerService.save(seller);
    }

	@DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) throws NotFoundException {
        sellerService.deleteById(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public SellerApi update(@RequestBody SellerApi sellerApi) throws NotFoundException, BadRequestException {
    	return sellerService.update(sellerApi);
    }
}
