package ar.com.flexibility.examen.domain.service.impl;


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

    public PurcharseServiceImpl(PurcharseRepository purcharseRepository) {
        this.purcharseRepository = purcharseRepository;
    }

    @Override
    public Purcharse addPurcharse(Purcharse purcharse) {
        Purcharse savedPurcharse = purcharseRepository.save(purcharse);

        checkServiceStatus(savedPurcharse,"The purcharse was saved successfully", "An error ocurred while saving the purcharse");

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

        Purcharse searchedPurcharse = purcharseRepository.findOne(id);

        checkSuccessfullyDelete(searchedPurcharse);
    }

    private void checkSuccessfullyDelete(Purcharse purcharse) {
        if (purcharse == null) {
            logger.info("The purcharse was deleted successfully");
        } else {
            logger.warn("An error ocurred while deleting the purcharse");
        }
    }

    @Override
    public List<Purcharse> findAll() {
        List<Purcharse> allPurcharses = (List<Purcharse>) purcharseRepository.findAll();

        checkSuccessfullyFindAll(allPurcharses);

        return allPurcharses;
    }

    private void checkSuccessfullyFindAll(List<Purcharse> allPurcharses) {
        if (allPurcharses != null) {
            logger.info("The purcharses were found successfully");
        } else {
            logger.warn("An error ocurred while searching the purcharses");
        }
    }
}
