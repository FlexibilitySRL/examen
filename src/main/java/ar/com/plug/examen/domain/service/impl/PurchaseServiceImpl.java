package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.DTO.PurchaseDTO;
import ar.com.plug.examen.app.mapper.PurchaseMapper;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.Vendor;
import ar.com.plug.examen.domain.repository.PurchaseRepository;
import ar.com.plug.examen.domain.repository.VendorRepository;
import ar.com.plug.examen.domain.service.PurchaseService;
import ar.com.plug.examen.exception.PurchaseNotFoundException;
import ar.com.plug.examen.exception.VendorNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of the PurchaseService interface.
 */
@Slf4j
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final VendorRepository vendorRepository;
    private final PurchaseMapper purchaseMapper;

    private final EntityManager entityManager;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, VendorRepository vendorRepository, PurchaseMapper purchaseMapper, EntityManager entityManager) {
        this.purchaseRepository = purchaseRepository;
        this.vendorRepository = vendorRepository;
        this.purchaseMapper = purchaseMapper;
        this.entityManager = entityManager;
    }

    @Override
    public PurchaseDTO updatePurchase(UUID id, PurchaseDTO purchaseDTO) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow(PurchaseNotFoundException::new);
        purchaseRepository.save(purchase);
        return purchaseMapper.asDTO(purchase);
    }

    @Override
    public PurchaseDTO getPurchaseById(UUID id) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow(PurchaseNotFoundException::new);
        return purchaseMapper.asDTO(purchase);
    }

    @Override
    public PurchaseDTO approvePurchase(UUID id, UUID vendorId) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow(PurchaseNotFoundException::new);
        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow(VendorNotFoundException::new);
        purchase.setVendor(vendor);
        purchase.setStatus(Purchase.PurchaseStatus.APPROVED);
        purchaseRepository.save(purchase);
        return purchaseMapper.asDTO(purchase);
    }

    @Override
    public List<PurchaseDTO> getPurchasesByCriteria(UUID customerId, UUID vendorId) {
        log.info("Fetching purchases by criteria");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Purchase> criteriaQuery = criteriaBuilder.createQuery(Purchase.class);

        Root<Purchase> purchaseRoot = criteriaQuery.from(Purchase.class);
        List<Predicate> predicates = new ArrayList<>();

        if (customerId != null) {
            predicates.add(criteriaBuilder.equal(purchaseRoot.get("customerId"), customerId));
        }

        if (vendorId != null) {
            predicates.add(criteriaBuilder.equal(purchaseRoot.get("vendorId"), vendorId));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(criteriaQuery).getResultList().stream()
                .map(purchaseMapper::asDTO)
                .collect(Collectors.toList());

    }
}
