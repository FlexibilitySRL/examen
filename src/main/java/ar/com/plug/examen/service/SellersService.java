package ar.com.plug.examen.service;

import ar.com.plug.examen.dao.SellersDAO;
import ar.com.plug.examen.model.Sellers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellersService {

    private final SellersDAO sellersDao;
    Logger logger = LoggerFactory.getLogger(SellersService.class);

    @Autowired
    public SellersService(SellersDAO sellersDao) {
        this.sellersDao = sellersDao;
    }

    public int insertSeller(Sellers seller) {
        try {
            sellersDao.save(seller);
            logger.info("insertSeller processed correctly");
            return 1;
        } catch (Exception e) {
            logger.error("Error "+e.getMessage());
            return 0;
        }
    }

    public int updateSeller(Sellers seller) {
        try {
            sellersDao.save(seller);
            logger.info("updateSeller processed correctly");
            return 1;
        } catch (Exception e) {
            logger.error("Error "+e.getMessage());
            return 0;
        }
    }

    public int deleteSeller (Long id){
        try {
            sellersDao.deleteById(id);
            logger.info("deleteSeller processed correctly");
            return 1;
        } catch (Exception e) {
            logger.error("Error "+e.getMessage());
            return 0;
        }
    }

    public List<Sellers> getAllSellers() {
        List<Sellers> sellers = sellersDao.findAll();
        logger.info("getAllSellers processed correctly");
        return sellers;
    }
}
