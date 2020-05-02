package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.SellerRepository;
import ar.com.flexibility.examen.domain.service.SellerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    private static final Logger logger = Logger.getLogger(SellerService.class);

    SellerRepository sellerRepository;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller addSeller(Seller seller) {
        Seller newSeller = sellerRepository.save(seller);

        if (newSeller == null) {
            logger.trace(String.format("Could not create the seller %s", seller.getFullName()));
        }

        return newSeller;
    }

    @Override
    public Seller updateSeller(Long id, Seller seller) {
        if (!sellerRepository.exists(id)) {
            logger.trace(String.format("Could not update the seller with id %s. It does not exist.", id));
            return null;
        }

        seller.setId(id);
        Seller updatedSeller = sellerRepository.save(seller);

        if (updatedSeller == null) {
            logger.trace(String.format("Could not update the seller with id %s", seller.getId()));
        }

        return updatedSeller;
    }

    @Override
    public boolean deleteSeller(Long id) {
        if (!sellerRepository.exists(id)) {
            logger.trace(String.format("Could not delete the seller with id %s. It does not exist.", id));
            return false;
        }

        try {
            sellerRepository.delete(id);
        } catch (Exception e) {
            logger.trace(String.format("Could not delete the seller with id %s. An internal error occurred: %s.",
                    id, e.getMessage()));
            return false;
        }

        return true;
    }

    @Override
    public List<Seller> retrieveSellers() {
        return (List<Seller>) sellerRepository.findAll();
    }

    @Override
    public Seller retrieveSellerById(Long id) {
        Seller seller = sellerRepository.findOne(id);

        if (seller == null) {
            logger.trace(String.format("Could not retrieve seller with id %s", id));
        }

        return seller;
    }
}
