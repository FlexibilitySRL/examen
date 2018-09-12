package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.api.SellApi;
import ar.com.flexibility.examen.app.rest.errors.InsufficientStockException;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Sell;
import ar.com.flexibility.examen.domain.service.mapper.SellMapper;
import ar.com.flexibility.examen.repository.ProductRepository;
import ar.com.flexibility.examen.repository.SellRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Sell.
 */
@Service
@Transactional
public class SellService {

    private final Logger log = LoggerFactory.getLogger(SellService.class);

    private final SellRepository sellRepository;

    private final ProductRepository productRepository;

    private final SellMapper sellMapper;

    public SellService(SellRepository sellRepository, SellMapper sellMapper, ProductRepository productRepository) {
        this.sellRepository = sellRepository;
        this.sellMapper = sellMapper;
        this.productRepository = productRepository;
    }

    /**
     * Save a sell.
     *
     * @param sellApi the entity to save
     * @return the persisted entity
     */
    public SellApi save(SellApi sellApi) {
        log.debug("Request to save Sell : {}", sellApi);

        //Decrease 1 element in the stock of the product
        Product product = productRepository.findOne(sellApi.getProductId());
        int stock = product.getStock();
        if (stock >= 1){
            product.setStock(stock-1);
            productRepository.saveAndFlush(product);
        }else{
            throw new InsufficientStockException( product.getTitle());
        }

        Sell sell = sellMapper.toEntity(sellApi);
        sell = sellRepository.save(sell);
        return sellMapper.toApi(sell);
    }

    /**
     * Get all the sells.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<SellApi> findAll(Pageable pageable) {
        log.debug("Request to get all sells");
        return sellRepository.findAll(pageable)
            .map(sellMapper::toApi);
    }

    /**
     * Get one sell by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public SellApi findOne(Long id) {
        log.debug("Request to get sell : {}", id);
        Sell sell = sellRepository.findOne(id);
        return sellMapper.toApi(sell);
    }

    /**
     * Delete the sell by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete sell : {}", id);
        sellRepository.delete(id);
    }
}
