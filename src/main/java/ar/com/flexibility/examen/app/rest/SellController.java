package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.SellApi;
import ar.com.flexibility.examen.app.rest.errors.BadRequestAlertException;
import ar.com.flexibility.examen.app.rest.util.HeaderUtil;
import ar.com.flexibility.examen.app.rest.util.PaginationUtil;
import ar.com.flexibility.examen.domain.model.Sell;
import ar.com.flexibility.examen.domain.service.mapper.SellMapper;
import ar.com.flexibility.examen.repository.SellRepository;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Sell.
 */
@RestController
@RequestMapping("/api")
public class SellController {

    private final Logger log = LoggerFactory.getLogger(SellController.class);

    private static final String ENTITY_NAME = "sell";

    private final SellRepository sellRepository;

    private final SellMapper sellMapper;

    public SellController(SellRepository sellRepository, SellMapper sellMapper) {
        this.sellRepository = sellRepository;
        this.sellMapper = sellMapper;
    }

    /**
     * POST  /sells : Create a new sell.
     *
     * @param sellApi the sellApi to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sellApi, or with status 400 (Bad Request) if the sell has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sells")
    @Timed
    public ResponseEntity<SellApi> createSell(@RequestBody SellApi sellApi) throws URISyntaxException {
        log.debug("REST request to save Sell : {}", sellApi);
        if (sellApi.getId() != null) {
            throw new BadRequestAlertException("A new sell cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Sell sell = sellMapper.toEntity(sellApi);
        sell = sellRepository.save(sell);
        SellApi result = sellMapper.toApi(sell);
        return ResponseEntity.created(new URI("/api/sells/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sells : Updates an existing sell.
     *
     * @param sellApi the sellApi to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sellApi,
     * or with status 400 (Bad Request) if the sellApi is not valid,
     * or with status 500 (Internal Server Error) if the sellApi couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sells")
    @Timed
    public ResponseEntity<SellApi> updateSell(@RequestBody SellApi sellApi) throws URISyntaxException {
        log.debug("REST request to update Sell : {}", sellApi);
        if (sellApi.getId() == null) {
            return createSell(sellApi);
        }
        Sell sell = sellMapper.toEntity(sellApi);
        sell = sellRepository.save(sell);
        SellApi result = sellMapper.toApi(sell);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sellApi.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sells : get all the sells.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of sells in body
     */
    @GetMapping("/sells")
    @Timed
    public ResponseEntity<List<SellApi>> getAllSells(Pageable pageable) {
        log.debug("REST request to get a page of Sells");
        Page<Sell> page = sellRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sells");
        return new ResponseEntity<>(sellMapper.toApi(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /sells/:id : get the "id" sell.
     *
     * @param id the id of the sellDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sellDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sells/{id}")
    @Timed
    public ResponseEntity<SellApi> getSell(@PathVariable Long id) {
        log.debug("REST request to get Sell : {}", id);
        Sell sell = sellRepository.findOne(id);
        SellApi sellApi = sellMapper.toApi(sell);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(sellApi));
    }

    /**
     * DELETE  /sells/:id : delete the "id" sell.
     *
     * @param id the id of the sellDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sells/{id}")
    @Timed
    public ResponseEntity<Void> deleteSell(@PathVariable Long id) {
        log.debug("REST request to delete Sell : {}", id);
        sellRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
