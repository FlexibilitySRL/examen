package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Purcharse;
import ar.com.flexibility.examen.domain.service.impl.PurcharseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purcharses")
public class PurcharseController {

    @Autowired
    private PurcharseServiceImpl purcharseService;

    @GetMapping
    public List<Purcharse> showPurcharses(){
        return purcharseService.findAll();
    }

    @PostMapping
    public void addPurcharse(Purcharse purcharse) {
        purcharseService.addPurcharse(purcharse);
    }

    @PutMapping
    public void updatePurcharse(@RequestBody Purcharse purcharse) {
        purcharseService.updatePurcharse(purcharse);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePurcharse(@PathVariable("id") Long id){
        purcharseService.deletePurcharse(id);
    }



}
