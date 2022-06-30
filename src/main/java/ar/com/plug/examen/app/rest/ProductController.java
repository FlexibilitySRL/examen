package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.rest.responses.ChallengeResponse;
import ar.com.plug.examen.domain.execptions.ChallengeException;
import ar.com.plug.examen.domain.execptions.NotFoundException;
import ar.com.plug.examen.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private static final String STATUS_SUCCESS = "Success";

    @Autowired
    ProductService productService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{productId}")
    public ChallengeResponse<ProductApi> getProductById(@PathVariable Long productId) throws ChallengeException {
        return new ChallengeResponse<>(STATUS_SUCCESS, String.valueOf(HttpStatus.OK), "OK", productService.getProductById(productId));
    }

    @PostMapping()
    public ChallengeResponse<ProductApi> createProduct(@RequestBody ProductApi productApi) {
        return new ChallengeResponse<>(STATUS_SUCCESS, String.valueOf(HttpStatus.CREATED), "OK", productService.createProduct(productApi));
    }

    @GetMapping()
    public ChallengeResponse<List<ProductApi>> listAllProducts() {
        return new ChallengeResponse<>(STATUS_SUCCESS, String.valueOf(HttpStatus.OK), "OK", productService.listAllProducts());
    }

    @DeleteMapping("/{id}")
    public ChallengeResponse removeProduct(@PathVariable Long id) throws NotFoundException {
        productService.removeProduct(id);
        return new ChallengeResponse<ProductApi>(STATUS_SUCCESS, String.valueOf(HttpStatus.OK), "OK");
    }

    @PutMapping(value = "/{id}")
    public ChallengeResponse<ProductApi> updateProduct(@PathVariable Long id, @RequestBody ProductApi productApi) throws NotFoundException {
        return new ChallengeResponse<>(STATUS_SUCCESS, String.valueOf(HttpStatus.OK), "OK", productService.updateProduct(id, productApi));
    }
}
