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

import ar.com.flexibility.examen.app.api.PurchaseApi;
import ar.com.flexibility.examen.app.api.StatusApi;
import ar.com.flexibility.examen.domain.service.PurchaseService;
import ar.com.flexibility.examen.exception.ClientException;
import ar.com.flexibility.examen.exception.PurchaseException;
import ar.com.flexibility.examen.utils.Authentication;
import ar.com.flexibility.examen.utils.CredentialException;

@RestController
@RequestMapping(path = "/purchase")
public class PurchaseController
{

    @Autowired
    PurchaseService purchaseService;

    @RequestMapping(path = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> add(@RequestBody PurchaseApi purchase,
            @RequestHeader(value = "authorization", required = true) String authorization)
            throws PurchaseException
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
        PurchaseApi newPurchase;

        try
        {
            newPurchase = purchaseService.add(purchase);
        }
        catch (PurchaseException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR",
                    "Error adding purchase.");
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<PurchaseApi>(newPurchase, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> update(@RequestBody PurchaseApi purchase,
            @RequestHeader(value = "authorization", required = true) String authorization)
            throws PurchaseException
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
        PurchaseApi newPurchase;
        try
        {
            newPurchase = purchaseService.update(purchase);
        }
        catch (PurchaseException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR",
                    "Error updating purchase.");
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<PurchaseApi>(newPurchase, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> remove(@PathVariable Long id,
            @RequestHeader(value = "authorization", required = true) String authorization)
            throws PurchaseException
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

        PurchaseApi newPurchase;

        try
        {
            newPurchase = purchaseService.remove(id);
        }
        catch (PurchaseException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR",
                    "Error removing purchase.");
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ;

        return new ResponseEntity<PurchaseApi>(newPurchase, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> get(@PathVariable Long id,
            @RequestHeader(value = "authorization", required = true) String authorization)
            throws PurchaseException
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
            PurchaseApi newPurchase = purchaseService.get(id);
            return new ResponseEntity<PurchaseApi>(newPurchase, HttpStatus.OK);
        }
        catch (PurchaseException e)
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
            throws PurchaseException
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

        List<PurchaseApi> newPurchaseList;

        try
        {
            newPurchaseList = purchaseService.getAll(page, pageSize);
        }
        catch (PurchaseException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR",
                    "Error getting purchases.");
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ;
        return new ResponseEntity<List<PurchaseApi>>(newPurchaseList,
                HttpStatus.OK);
    }

}
