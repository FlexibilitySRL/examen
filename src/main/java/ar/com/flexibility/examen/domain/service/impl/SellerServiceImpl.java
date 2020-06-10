package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.api.SellerApi;
import ar.com.flexibility.examen.domain.exceptions.BadRequestException;
import ar.com.flexibility.examen.domain.exceptions.NotFoundException;
import ar.com.flexibility.examen.domain.mappers.EntityApiMapper;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repositories.SellerRepository;
import ar.com.flexibility.examen.domain.service.SellerService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    private final Logger logger = LoggerFactory.getLogger(SellerServiceImpl.class);

    private SellerRepository sellerRepository;
    private EntityApiMapper entityApiMapper;

    @Override
    public SellerApi create(SellerApi sellerApi) {
        logger.debug("Validating required data...");
        if (StringUtils.isBlank(sellerApi.getName())) {
            logger.error("The name is required");
            throw new BadRequestException("Required data is missing: name");
        }
        Seller seller = entityApiMapper.destinationToSourceSellerApi(sellerApi);
        sellerRepository.save(seller);

        logger.info("Product created {}", seller.getId());
        return entityApiMapper.sourceToDestinationSeller(seller);
    }

    @Override
    public SellerApi retrieve(Long id) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Seller with id " + id));

        return entityApiMapper.sourceToDestinationSeller(seller);
    }

    @Override
    public List<SellerApi> list() {
        List<Seller> sellers = sellerRepository.findAll();
        return entityApiMapper.sourceToDestinationSellers(sellers);
    }

    @Override
    public void remove(Long id) {
        if (!sellerRepository.exists(id)) {
            throw new NotFoundException("Seller with id " + id);
        }
        sellerRepository.delete(id);
    }

    @Override
    public SellerApi update(Long id, SellerApi sellerApi) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Seller with id " + id));

        seller.setName(sellerApi.getName());
        return entityApiMapper.sourceToDestinationSeller(sellerRepository.save(seller));
    }

    @Autowired
    public void setSellerRepository(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Autowired
    public void setEntityApiMapper(EntityApiMapper entityApiMapper) {
        this.entityApiMapper = entityApiMapper;
    }
}
