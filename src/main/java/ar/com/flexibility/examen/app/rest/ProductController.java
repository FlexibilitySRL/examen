package ar.com.flexibility.examen.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.app.api.StatusApi;
import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.exception.ClientException;
import ar.com.flexibility.examen.exception.ProductException;
import ar.com.flexibility.examen.utils.Authentication;
import ar.com.flexibility.examen.utils.CredentialException;

@RestController
@RequestMapping(path = "/product")
public class ProductController
{

    @Autowired
    ProductService productService;

    @RequestMapping(path = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> add(@RequestBody ProductApi product,
            @RequestHeader(value = "authorization", required = true) String authorization)
            throws ProductException
    {
        try
        {
            Authentication.checkCredentials(authorization);
        }
        catch (CredentialException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR", e.getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ProductApi newProduct;

        try
        {
            newProduct = productService.add(product);
        }
        catch (ProductException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR",
                    "Error adding product.");
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ProductApi>(newProduct, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> update(@RequestBody ProductApi product,
            @RequestHeader(value = "authorization", required = true) String authorization)
            throws ProductException
    {
        try
        {
            Authentication.checkCredentials(authorization);
        }
        catch (CredentialException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR", e.getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ProductApi newProduct;
        try
        {
            newProduct = productService.update(product);
        }
        catch (ProductException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR",
                    "Error updating product.");
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ProductApi>(newProduct, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> remove(@PathVariable Long id,
            @RequestHeader(value = "authorization", required = true) String authorization)
            throws ProductException
    {
        try
        {
            Authentication.checkCredentials(authorization);
        }
        catch (CredentialException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR", e.getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ProductApi newProduct;

        try
        {
            newProduct = productService.remove(id);
        }
        catch (ProductException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR",
                    "Error removing product.");
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ;

        return new ResponseEntity<ProductApi>(newProduct, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> get(@PathVariable Long id,
            @RequestHeader(value = "authorization", required = true) String authorization)
            throws ProductException
    {
        try
        {
            Authentication.checkCredentials(authorization);
        }
        catch (CredentialException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR", e.getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try
        {
            ProductApi newProduct = productService.get(id);
            return new ResponseEntity<ProductApi>(newProduct, HttpStatus.OK);
        }
        catch (ProductException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR", e.getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(path = "/", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page", required = false) Long page,
            @RequestParam(value = "pageSize", required = false) Long pageSize,
            @RequestHeader(value = "authorization", required = true) String authorization)
            throws ProductException
    {

        try
        {
            Authentication.checkCredentials(authorization);
        }
        catch (CredentialException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR", e.getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<ProductApi> newProductList;

        try
        {
            newProductList = productService.getAll(page, pageSize);
        }
        catch (ProductException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR",
                    "Error getting products.");
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ;
        return new ResponseEntity<List<ProductApi>>(newProductList,
                HttpStatus.OK);
    }

}
