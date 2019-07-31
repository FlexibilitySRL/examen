package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purcharse;
import ar.com.flexibility.examen.domain.repository.PurcharseRepository;
import ar.com.flexibility.examen.domain.service.PurcharseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PurcharseServiceImpl implements PurcharseService {
    private Logger logger = LoggerFactory.getLogger("ar.com.flexibility.examen.domain.service.impl.PurcharseServiceImpl");
    private PurcharseRepository purcharseRepository;
    private ProductServiceImpl productService;

    public PurcharseServiceImpl(PurcharseRepository purcharseRepository, ProductServiceImpl productService) {
        this.purcharseRepository = purcharseRepository;
        this.productService = productService;
    }

    @Override
    public Purcharse addPurcharse(Purcharse purcharse) {
        Purcharse savedPurcharse = purcharseRepository.save(purcharse);

        checkServiceStatus(savedPurcharse, "The purcharse was saved successfully", "An error ocurred while saving the purcharse");

        return savedPurcharse;
    }

    private void checkServiceStatus(Purcharse purcharse, String infoMessage, String warningMessage) {
        if (purcharse != null) {
            logger.info(infoMessage);
        } else {
            logger.warn(warningMessage);
        }
    }

    @Override
    public Purcharse addProduct(Purcharse purcharse, Product product) {
        checkValidParameters(purcharse, product);
        Purcharse searchedPurcharse = findById(purcharse.getId());
        Product searchedProduct = productService.findById(product.getId());

        searchedPurcharse.add(searchedProduct);
        Purcharse updatedPurcharse = updatePurcharse(searchedPurcharse);

        checkServiceStatus(updatedPurcharse, "The product was added successfully", "An error ocurred while adding the product");

        return updatedPurcharse;
    }

    private void checkValidParameters(Purcharse purcharse, Product product) {
        if (purcharse == null) {
            throw new IllegalArgumentException("The purcharse cannot be null");
        }
        if (product == null) {
            throw new IllegalArgumentException("The product cannot be null");
        }
    }

    @Override
    public Purcharse updatePurcharse(Purcharse purcharse) {
        Purcharse updatedPurcharse = purcharseRepository.save(purcharse);

        checkServiceStatus(updatedPurcharse, "The purcharse was updated successfully", "An error ocurred while updating the purcharse");

        return updatedPurcharse;
    }

    @Override
    public Purcharse findById(Long id) {
        Purcharse searchedPurcharse = purcharseRepository.findOne(id);

        checkServiceStatus(searchedPurcharse, "The purcharse was searched successfully", "An error ocurred while searching the purcharse");

        return searchedPurcharse;
    }

    @Override
    public void deletePurcharse(Long id) {
        purcharseRepository.delete(id);

        Purcharse searchedPurcharse = findById(id);

        checkSuccessfullyDelete(searchedPurcharse);
    }

    private void checkSuccessfullyDelete(Purcharse purcharse) {
        if (purcharse == null) {
            logger.info("The purcharse was deleted successfully");
        } else {
            logger.warn("An error ocurred while deleting the shopping list");
        }
    }

    @Override
    public List<Purcharse> findAll() {
        List<Purcharse> allPurcharses = (List<Purcharse>) purcharseRepository.findAll();

        checkSuccessfullyFindAll(allPurcharses);

        return allPurcharses;
    }

    private void checkSuccessfullyFindAll(List<Purcharse> allProducts) {
        if (allProducts != null) {
            logger.info("The product was found successfully");
        } else {
            logger.warn("An error ocurred while searching the product");
        }
    }
}
