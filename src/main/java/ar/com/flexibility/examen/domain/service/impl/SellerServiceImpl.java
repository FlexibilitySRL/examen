package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.SellerRepository;
import ar.com.flexibility.examen.domain.service.SellerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  Implementation of the SellerService that uses a CrudRepository. It
 *  connects to a Database defined in the application.yml file.
 *
 * @author  Amador Cuenca <sphi02ac@gmail.com>
 * @version 1.0
 */
@Service
public class SellerServiceImpl implements SellerService {

    private static final Logger logger = Logger.getLogger(SellerService.class);

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
//    public SellerServiceImpl(SellerRepository sellerRepository) {
//        this.sellerRepository = sellerRepository;
//    }

    /**
     *  Persists a new Seller in the repository.
     *
     * @author  Amador Cuenca <sphi02ac@gmail.com>
     * @version 1.0
     * @param seller Seller POJO (without ID)
     * @return Seller POJO (with ID) or null if there was an error.
     */
    @Override
    public Seller addSeller(Seller seller) {
        Seller newSeller = sellerRepository.save(seller);

        if (newSeller == null) {
            logger.trace(String.format("Could not create the seller %s", seller.getFullName()));
        }

        return newSeller;
    }

    /**
     *  Persists changes to a Seller model to the repository.
     *
     * @author  Amador Cuenca <sphi02ac@gmail.com>
     * @version 1.0
     * @param id Seller ID
     * @param seller Seller POJO
     * @return Updated POJO or null if there was an error.
     */
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

    /**
     *  Deletes a Seller model from the repository.
     *
     * @author  Amador Cuenca <sphi02ac@gmail.com>
     * @version 1.0
     * @param id Seller ID
     * @return true if it was deleted, otherwise false.
     */
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

    /**
     *  Retrieves a list of Seller models from the repository.
     *
     * @author  Amador Cuenca <sphi02ac@gmail.com>
     * @version 1.0
     * @return List of client models.
     */
    @Override
    public List<Seller> retrieveSellers() {
        return (List<Seller>) sellerRepository.findAll();
    }

    /**
     *  Retrieves a Seller model from the repository.
     *
     * @author  Amador Cuenca <sphi02ac@gmail.com>
     * @version 1.0
     * @param id Seller ID
     * @return Seller POJO or null if it does not exist.
     */
    @Override
    public Seller retrieveSellerById(Long id) {
        Seller seller = sellerRepository.findOne(id);

        if (seller == null) {
            logger.trace(String.format("Could not retrieve seller with id %s", id));
        }

        return seller;
    }
}
