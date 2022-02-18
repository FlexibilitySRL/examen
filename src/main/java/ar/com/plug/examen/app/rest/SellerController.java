package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.SellerDTO;
import ar.com.plug.examen.domain.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @PostMapping(path = "/saveSeller",  produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public void createSeller(@RequestBody SellerDTO sellerDTO) {
        sellerService.createSeller(sellerDTO);
    }

    @DeleteMapping(path = "/removeSeller/{idSeller}",  produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSeller(@PathVariable("idSeller") Long id) {
        sellerService.deleteSeller(id);
    }

    @PostMapping(path = "/editSeller/{idSeller}",  produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public void modifySeller(@PathVariable("idSeller") Long id, @RequestBody SellerDTO sellerDTO) {
        sellerService.editSeller(id, sellerDTO);
    }
}
