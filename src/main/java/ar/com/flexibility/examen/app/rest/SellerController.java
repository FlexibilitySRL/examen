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

import ar.com.flexibility.examen.app.api.SellerApi;
import ar.com.flexibility.examen.app.api.StatusApi;
import ar.com.flexibility.examen.domain.service.SellerService;
import ar.com.flexibility.examen.exception.SellerException;
import ar.com.flexibility.examen.utils.Authentication;
import ar.com.flexibility.examen.utils.CredentialException;

@RestController
@RequestMapping(path = "/seller")
public class SellerController
{

    @Autowired
    SellerService sellerService;

    @RequestMapping(path = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> add(@RequestBody SellerApi seller,
            @RequestHeader(value = "authorization", required = true) String authorization)
            throws SellerException
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

        SellerApi newSeller;

        try
        {
            newSeller = sellerService.add(seller);
        }
        catch (SellerException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR",
                    "Error adding seller.");
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<SellerApi>(newSeller, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> update(@RequestBody SellerApi seller,
            @RequestHeader(value = "authorization", required = true) String authorization)
            throws SellerException
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

        SellerApi newSeller;

        try
        {
            newSeller = sellerService.update(seller);
        }
        catch (SellerException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR",
                    "Error updating seller.");
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<SellerApi>(newSeller, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> remove(@PathVariable Long id,
            @RequestHeader(value = "authorization", required = true) String authorization)
            throws SellerException
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

        SellerApi newSeller;

        try
        {
            newSeller = sellerService.remove(id);
        }
        catch (SellerException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR",
                    "Error removing seller.");
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ;

        return new ResponseEntity<SellerApi>(newSeller, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> get(@PathVariable Long id,
            @RequestHeader(value = "authorization", required = true) String authorization)
            throws SellerException
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
            SellerApi newSeller = sellerService.get(id);
            return new ResponseEntity<SellerApi>(newSeller, HttpStatus.OK);
        }
        catch (SellerException e)
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
            throws SellerException
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

        List<SellerApi> newSellerList;

        try
        {
            newSellerList = sellerService.getAll(page, pageSize);
        }
        catch (SellerException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR",
                    "Error getting sellers.");
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ;
        return new ResponseEntity<List<SellerApi>>(newSellerList,
                HttpStatus.OK);
    }
}
