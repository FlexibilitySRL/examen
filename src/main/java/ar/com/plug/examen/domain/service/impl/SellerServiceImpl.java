package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.service.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    @Override
    public List<Seller> listAllSeller() {
        return sellerRepository.findAll();
    }

    @Override
    public Seller getSeller(Long id) {
        return sellerRepository.findById(id).orElse(null);
    }

    @Override
    public Seller createSeller(Seller seller) {
        Optional<Seller> sellerDB = Optional.ofNullable(sellerRepository.findByNumberId(seller.getNumberId()));
        if(sellerDB.isPresent()){
            log.error("Seller already exits in database: {} ",  seller.getFirstName());
            return sellerDB.get();
        }
        try {
            Seller sellerCreated = sellerRepository.save(seller);
            log.info("Seller created: {}", seller.getFirstName());
            return sellerCreated;
        } catch (DataIntegrityViolationException err){
            log.error("Error en la creacion de vendedor: {}", err.getRootCause().toString());
            return null;
        }

    }

    @Override
    public Seller updateSeller(Seller seller) {
        Optional<Seller> sellerDB = Optional.ofNullable(getSeller(seller.getId()));
        if (sellerDB.isEmpty()){
            log.error("Seller not found: {}", seller.getFirstName());
            return null;
        }
        Seller sellerUpdated = sellerDB.get();
        sellerUpdated.setFirstName(seller.getFirstName());
        sellerUpdated.setLastName(seller.getLastName());
        if(seller.getPhotoUrl() != null) sellerUpdated.setPhotoUrl(seller.getPhotoUrl());
        log.info("Seller success updated: {}", seller.getFirstName());
        return sellerRepository.save(sellerUpdated);
    }

    @Override
    public Seller deleteSeller(Long id) {
        Optional<Seller> sellerDB = Optional.ofNullable(getSeller(id));
        if (sellerDB.isEmpty()){
            log.error("Seller not found");
            return null;
        }
        sellerRepository.delete(sellerDB.get());
        log.info("Seller success deleted: {}", sellerDB.get().getFirstName());
        return sellerDB.get();
    }

}
