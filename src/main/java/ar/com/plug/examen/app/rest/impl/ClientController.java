package ar.com.plug.examen.app.rest.impl;

import ar.com.plug.examen.app.api.ClientDTO;
import ar.com.plug.examen.app.dtoResponse.ClientResponseDTO;
import ar.com.plug.examen.app.dtoResponse.ListClientResponseDTO;
import ar.com.plug.examen.app.rest.IClientController;
import ar.com.plug.examen.domain.service.IClientRequestService;

import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ClientController implements IClientController {

    Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private IClientRequestService clientRequestService;

    public ResponseEntity<ClientResponseDTO> addClientRequest(@ApiParam(name = "client_request", value = "New client request", required = true) @RequestBody @Valid ClientDTO request) throws Exception {
        log.info("/add_client - POST - Body Request: " + request.toString());
        ClientResponseDTO dto = clientRequestService.create(request);
        log.info("/add_client - POST - Body Response: " + dto.toString());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public ResponseEntity<ClientResponseDTO> updateClientRequest(@ApiParam(name = "client_request", value = "Update client request", required = true) @RequestBody @Valid ClientDTO request) throws Exception {
        log.info("/update_client - PUT - Body Request: " + request.toString());
        ClientResponseDTO dto = clientRequestService.update(request);
        log.info("/update_client - PUT - Body Response: " + dto.toString());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public ResponseEntity<ClientResponseDTO> removeClientRequest(@ApiParam(name = "client_request", value = "Remove client request", required = true) @PathVariable Integer document) throws Exception {
        log.info("/remove_client - DELETE - Path Variable: " + document);
        ClientResponseDTO dto = clientRequestService.delete(document);
        log.info("/remove_client - DELETE - Body Response: " + dto.toString());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public ResponseEntity<ListClientResponseDTO> listClientRequest() throws Exception {
        log.info("/list_client - GET");
        ListClientResponseDTO listDto = clientRequestService.list();
        log.info("/list_client - GET - Body Response: " + listDto.toString());
        return new ResponseEntity<>(listDto, HttpStatus.OK);
    }

}
