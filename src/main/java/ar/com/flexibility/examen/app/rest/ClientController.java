package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.rest.errors.BadRequestAlertException;
import ar.com.flexibility.examen.app.rest.util.HeaderUtil;
import ar.com.flexibility.examen.app.rest.util.PaginationUtil;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.service.mapper.ClientMapper;
import ar.com.flexibility.examen.repository.ClientRepository;
import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Client.
 */
@RestController
@RequestMapping("/api")
public class ClientController {

    private final Logger log = LoggerFactory.getLogger(ClientController.class);

    private static final String ENTITY_NAME = "client";

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    public ClientController(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    /**
     * POST  /clients : Create a new client.
     *
     * @param clientApi the clientApi to create
     * @return the ResponseEntity with status 201 (Created) and with body the new clientApi, or with status 400 (Bad Request) if the client has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/clients")
    @Timed
    public ResponseEntity<ClientApi> createClient(@Valid @RequestBody ClientApi clientApi) throws URISyntaxException {
        log.debug("REST request to save Client : {}", clientApi);
        if (clientApi.getId() != null) {
            throw new BadRequestAlertException("A new client cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Client client = clientMapper.toEntity(clientApi);
        client = clientRepository.save(client);
        ClientApi result = clientMapper.toApi(client);
        return ResponseEntity.created(new URI("/api/clients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /clients : Updates an existing client.
     *
     * @param clientApi the clientApi to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated clientApi,
     * or with status 400 (Bad Request) if the clientApi is not valid,
     * or with status 500 (Internal Server Error) if the clientApi couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/clients")
    @Timed
    public ResponseEntity<ClientApi> updateClient(@Valid @RequestBody ClientApi clientApi) throws URISyntaxException {
        log.debug("REST request to update Client : {}", clientApi);
        if (clientApi.getId() == null) {
            return createClient(clientApi);
        }
        Client client = clientMapper.toEntity(clientApi);
        client = clientRepository.save(client);
        ClientApi result = clientMapper.toApi(client);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, clientApi.getId().toString()))
            .body(result);
    }

    /**
     * GET  /clients : get all the clients.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of clients in body
     */
    @GetMapping("/clients")
    @Timed
    public ResponseEntity<List<ClientApi>> getAllClients(Pageable pageable) {
        log.debug("REST request to get a page of Clients");
        Page<Client> page = clientRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/clients");
        return new ResponseEntity<>(clientMapper.toApi(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /clients/:id : get the "id" client.
     *
     * @param id the id of the clientDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the clientDTO, or with status 404 (Not Found)
     */
    @GetMapping("/clients/{id}")
    @Timed
    public ResponseEntity<ClientApi> getClient(@PathVariable Long id) {
        log.debug("REST request to get Client : {}", id);
        Client client = clientRepository.findOne(id);
        ClientApi clientApi = clientMapper.toApi(client);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(clientApi));
    }

    /**
     * DELETE  /clients/:id : delete the "id" client.
     *
     * @param id the id of the clientDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/clients/{id}")
    @Timed
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        log.debug("REST request to delete Client : {}", id);
        clientRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
