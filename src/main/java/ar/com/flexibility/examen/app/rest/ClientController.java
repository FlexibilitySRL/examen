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

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.api.StatusApi;
import ar.com.flexibility.examen.domain.service.ClientService;
import ar.com.flexibility.examen.exception.ClientException;
import ar.com.flexibility.examen.utils.Authentication;
import ar.com.flexibility.examen.utils.CredentialException;

@RestController
@RequestMapping(path = "/client")
public class ClientController
{

    @Autowired
    ClientService clientService;

    @RequestMapping(path = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> add(@RequestBody ClientApi client,
            @RequestHeader(value = "authorization", required = true) String authorization)
            throws ClientException
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

        ClientApi newClient;

        try
        {
            newClient = clientService.add(client);
        }
        catch (ClientException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR",
                    "Error adding client.");
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ClientApi>(newClient, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> update(@RequestBody ClientApi client,
            @RequestHeader(value = "authorization", required = true) String authorization)
            throws ClientException
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

        ClientApi newClient;

        try
        {
            newClient = clientService.update(client);
        }
        catch (ClientException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR",
                    "Error updating client.");
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ClientApi>(newClient, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> remove(@PathVariable Long id,
            @RequestHeader(value = "authorization", required = true) String authorization)
            throws ClientException
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

        ClientApi newClient;

        try
        {
            newClient = clientService.remove(id);
        }
        catch (ClientException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR",
                    "Error removing client.");
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ;

        return new ResponseEntity<ClientApi>(newClient, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> get(@PathVariable Long id,
            @RequestHeader(value = "authorization", required = true) String authorization)
            throws ClientException
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
            ClientApi newClient = clientService.get(id);
            return new ResponseEntity<ClientApi>(newClient, HttpStatus.OK);
        }
        catch (ClientException e)
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
            throws ClientException
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

        List<ClientApi> newClientList;

        try
        {
            newClientList = clientService.getAll(page, pageSize);
        }
        catch (ClientException e)
        {
            StatusApi statusApi = new StatusApi(e.getErrorCode(), e
                    .getMessage());
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e)
        {
            StatusApi statusApi = new StatusApi("ERROR",
                    "Error getting clients.");
            return new ResponseEntity<StatusApi>(statusApi,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ;
        return new ResponseEntity<List<ClientApi>>(newClientList,
                HttpStatus.OK);
    }
}
