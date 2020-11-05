package ar.com.plug.examen.service;

import ar.com.plug.examen.dao.SellersDAO;
import ar.com.plug.examen.model.Sellers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellersService {

    private final SellersDAO sellersDao;

    @Autowired
    public SellersService(SellersDAO sellersDao) {
        this.sellersDao = sellersDao;
    }

    public int insertSeller(Sellers seller) {
        try {
            sellersDao.save(seller);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int updateSeller(Sellers seller) {
        try {
            sellersDao.save(seller);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int deleteSeller (Long id){
        try {
            sellersDao.deleteById(id);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public List<Sellers> getAllSellers() {
        List<Sellers> sellers = sellersDao.findAll();
        return sellers;
    }
}
