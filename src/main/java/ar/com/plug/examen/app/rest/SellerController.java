package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.SellerDTO;
import ar.com.plug.examen.domain.service.SellerService;
import ar.com.plug.examen.domain.validators.Validator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/sellers")
public class SellerController
{
    @Autowired
    private SellerService sellerService;

    @Autowired
    private Validator validator;


    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody SellerDTO sellerDTO)
    {
        validator.validateSellerDTO(sellerDTO, Boolean.FALSE);
        return new ResponseEntity<>(sellerService.save(sellerDTO), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<SellerDTO>> getAllSellers()
    {
        return new ResponseEntity<>(sellerService.getAllSellers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "API to get seller by id", consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSellerById(@PathVariable Long id)
    {
        return new ResponseEntity<>(sellerService.getSellerById(id), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<SellerDTO> update(@RequestBody SellerDTO sellerDTO) {

        validator.validateSellerDTO(sellerDTO, Boolean.TRUE);
        return new ResponseEntity<>(sellerService.update(sellerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        sellerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
