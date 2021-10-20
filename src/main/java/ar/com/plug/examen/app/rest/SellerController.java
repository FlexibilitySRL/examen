package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.SellerDTO;
import ar.com.plug.examen.domain.service.SellerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Seller Controller
 */
@RestController
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;


    /**
     * Save a new Seller
     * @param sellerDTO
     * @return ResponseEntity<SellerDTO>
     */
    @PostMapping("save")
    @ApiOperation("Registrar un nuevo vendedor")
    @ApiResponse(code = 201, message = "CREATED")
    public ResponseEntity<SellerDTO> save(@RequestBody SellerDTO sellerDTO){

        return new ResponseEntity<>(sellerService.save(sellerDTO), HttpStatus.CREATED);
    }

    /**
     * update a seller if exist
     * @param sellerDTO
     * @return ResponseEntity<SellerDTO>
     */
    @PutMapping("update")
    @ApiOperation("Actualizar  un vendedor")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<SellerDTO> update(@RequestBody SellerDTO sellerDTO){

        return new ResponseEntity<>(sellerService.update(sellerDTO), HttpStatus.OK);
    }

    /**
     * Find a seller given its Id
     * @param sellerId
     * @return ResponseEntity<SellerDTO>
     */
    @GetMapping("/{id}")
    @ApiOperation("Buscar vendedor por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND"),
    })
    public ResponseEntity<SellerDTO> findById(
            @ApiParam(value = "Id del vendedor a consultar", required = true, example = "2")
            @PathVariable("id") long sellerId){

        return sellerService.findById(sellerId)
                .map(seller -> new ResponseEntity<>(seller, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Find a list of sellers which state is Active
     * @return ResponseEntity<List<SellerDTO>>
     */
    @GetMapping("/actives")
    @ApiOperation("Buscar todos los vendedores que se encuentren activos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    public ResponseEntity<List<SellerDTO>> getAllActive(){
         return sellerService.getAllActive()
                 .map(sellers -> new ResponseEntity<>(sellers,HttpStatus.OK))
                 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Find all sellers
     * @return ResponseEntity<List<SellerDTO>>
     */
    @GetMapping("/all")
    @ApiOperation("Buscar todos los vendedores")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<SellerDTO>> getAll(){
        return  new ResponseEntity<>(sellerService.getAll(), HttpStatus.OK);

    }

}
