package ar.com.plug.examen.app.rest;


import ar.com.plug.examen.domain.model.productModel;
import ar.com.plug.examen.domain.model.sellerModel;
import ar.com.plug.examen.domain.service.sellerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sellers")
public class sellerController {

    @Autowired
    sellerService sellerService;

    @GetMapping("/getAll")
    @ApiOperation("Get All Sellers")
    private ResponseEntity<List<sellerModel>> getAllSellers(){
        return ResponseEntity.ok(sellerService.getAllSellers());
    }

    @PostMapping("/save")
    @ApiOperation("Save a Seller")
    public ResponseEntity<sellerModel> saveSeller(@RequestBody sellerModel sellerModel){

        try
        {
            sellerModel sellerSaved = sellerService.saveSeller(sellerModel);
            return ResponseEntity.created(new URI("/sellers/"+sellerSaved.getId())).body(sellerSaved);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    @ApiOperation("Delete a Seller by Id")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
        boolean ok = this.sellerService.deleteSeller(id);

        if (ok) return ResponseEntity.ok("Se eliminó el vendedor con id " + id);
        else return ResponseEntity.badRequest().body("No se pudo eliminar el vendedor con id" + id);
    }

}
