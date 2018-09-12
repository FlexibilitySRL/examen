package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.SellerApi;
import ar.com.flexibility.examen.app.rest.errors.BadRequestAlertException;
import ar.com.flexibility.examen.app.rest.util.HeaderUtil;
import ar.com.flexibility.examen.app.rest.util.PaginationUtil;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.service.mapper.SellerMapper;
import ar.com.flexibility.examen.repository.SellerRepository;
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
 * REST controller for managing Seller.
 */
@RestController
@RequestMapping("/api")
public class SellerController {

    private final Logger log = LoggerFactory.getLogger(SellerController.class);

    private static final String ENTITY_NAME = "seller";

    private final SellerRepository sellerRepository;

    private final SellerMapper sellerMapper;

    public SellerController(SellerRepository sellerRepository, SellerMapper sellerMapper) {
        this.sellerRepository = sellerRepository;
        this.sellerMapper = sellerMapper;
    }

    /**
     * POST  /sellers : Create a new seller.
     *
     * @param sellerApi the sellerApi to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sellerApi, or with status 400 (Bad Request) if the seller has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sellers")
    @Timed
    public ResponseEntity<SellerApi> createSeller(@Valid @RequestBody SellerApi sellerApi) throws URISyntaxException {
        log.debug("REST request to save Seller : {}", sellerApi);
        if (sellerApi.getId() != null) {
            throw new BadRequestAlertException("A new seller cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Seller seller = sellerMapper.toEntity(sellerApi);
        seller = sellerRepository.save(seller);
        SellerApi result = sellerMapper.toApi(seller);
        return ResponseEntity.created(new URI("/api/sellers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sellers : Updates an existing seller.
     *
     * @param sellerApi the sellerApi to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sellerApi,
     * or with status 400 (Bad Request) if the sellerApi is not valid,
     * or with status 500 (Internal Server Error) if the sellerApi couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sellers")
    @Timed
    public ResponseEntity<SellerApi> updateSeller(@Valid @RequestBody SellerApi sellerApi) throws URISyntaxException {
        log.debug("REST request to update Seller : {}", sellerApi);
        if (sellerApi.getId() == null) {
            return createSeller(sellerApi);
        }
        Seller seller = sellerMapper.toEntity(sellerApi);
        seller = sellerRepository.save(seller);
        SellerApi result = sellerMapper.toApi(seller);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sellerApi.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sellers : get all the sellers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of sellers in body
     */
    @GetMapping("/sellers")
    @Timed
    public ResponseEntity<List<SellerApi>> getAllSellers(Pageable pageable) {
        log.debug("REST request to get a page of Sellers");
        Page<Seller> page = sellerRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sellers");
        return new ResponseEntity<>(sellerMapper.toApi(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /sellers/:id : get the "id" seller.
     *
     * @param id the id of the sellerDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sellerDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sellers/{id}")
    @Timed
    public ResponseEntity<SellerApi> getSeller(@PathVariable Long id) {
        log.debug("REST request to get Seller : {}", id);
        Seller seller = sellerRepository.findOne(id);
        SellerApi sellerApi = sellerMapper.toApi(seller);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(sellerApi));
    }

    /**
     * DELETE  /sellers/:id : delete the "id" seller.
     *
     * @param id the id of the sellerDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sellers/{id}")
    @Timed
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) {
        log.debug("REST request to delete Seller : {}", id);
        sellerRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
