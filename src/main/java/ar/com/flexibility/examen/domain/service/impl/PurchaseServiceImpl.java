package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.exception.FlexibilityException;
import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.model.PurchaseStatus;
import ar.com.flexibility.examen.domain.repository.PurchaseRepository;
import ar.com.flexibility.examen.domain.service.PurchaseService;
import ar.com.flexibility.examen.domain.service.dto.PurchaseDTO;
import ar.com.flexibility.examen.domain.service.mapper.PurchaseMapper;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final Logger log = LoggerFactory.getLogger(PurchaseServiceImpl.class);

    private final PurchaseRepository purchaseRepository;

    private final PurchaseMapper purchaseMapper;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, PurchaseMapper purchaseMapper) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseMapper = purchaseMapper;
    }

    /**
     * Get one purchase by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @ApiOperation("Get a Purchase")
    @Override
    public Optional<PurchaseDTO> findOne(Long id) {
        log.debug("Request to get Purchase : {}", id);
        Purchase product = purchaseRepository.findOne(id);
        return Optional.ofNullable(purchaseMapper.toDTO(product));
    }

    /**
     * Get all the purchases.
     *
     * @return the list of entities.
     */
    @ApiOperation("Get all Purchases")
    @Override
    public List<PurchaseDTO> findAll() {
        log.debug("Request to get all Purchases");
        return purchaseRepository.findAll().stream()
                .map(purchaseMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * Approve a purchase by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @ApiOperation("Approve a Purchase")
    @Override
    public PurchaseDTO approve(Long id) {
        log.debug("Request to approve Purchase : {}", id);

      Purchase purchase = purchaseRepository.findOne(id);

      if (ObjectUtils.isEmpty(purchase)) {
          throw new FlexibilityException("Purchase with id ".concat(id.toString()).concat(" not found"));
      }

      else if(purchase.getPurchaseStatus().equals(PurchaseStatus.APPROVED)) {
          throw new FlexibilityException("Purchase with id".concat(id.toString()).concat(" already approved"));
      }

      purchase.setPurchaseStatus(PurchaseStatus.APPROVED);

      purchase = purchaseRepository.save(purchase);

      return purchaseMapper.toDTO(purchase);
    }

}
