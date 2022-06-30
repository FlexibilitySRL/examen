package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.app.rest.responses.ChallengeResponse;
import ar.com.plug.examen.domain.execptions.NotFoundException;
import ar.com.plug.examen.domain.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/seller")
public class SellerController {

    private static final String STATUS_SUCCESS = "Success";

    @Autowired
    private SellerService sellerService;

    @PostMapping()
    public ChallengeResponse<SellerApi> createSeller(@RequestBody SellerApi sellerApi) {
        return new ChallengeResponse<>(STATUS_SUCCESS, String.valueOf(HttpStatus.OK), "OK", sellerService.createSeller(sellerApi));
    }

    @GetMapping(path = "/{id}")
    public ChallengeResponse<SellerApi> getSeller(@PathVariable Long id) throws NotFoundException {
        return new ChallengeResponse<>(STATUS_SUCCESS, String.valueOf(HttpStatus.OK), "OK", sellerService.getSellerById(id));
    }

    @DeleteMapping("/{id}")
    public ChallengeResponse removeSeller(@PathVariable Long id) throws NotFoundException {
        sellerService.removeSeller(id);
        return new ChallengeResponse<>(STATUS_SUCCESS, String.valueOf(HttpStatus.OK), "OK");

    }

    @PutMapping(value = "/{id}")
    public ChallengeResponse<SellerApi> updateSeller(@PathVariable Long id, @RequestBody SellerApi sellerApi) throws NotFoundException {
        return new ChallengeResponse<>(STATUS_SUCCESS, String.valueOf(HttpStatus.OK), "OK", sellerService.updateSeller(id, sellerApi));
    }

}
