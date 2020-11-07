package ar.com.plug.examen.service;

import ar.com.plug.examen.dao.ProductDAO;
import ar.com.plug.examen.model.Products;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductDAO productDao;
    Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    public ProductService(ProductDAO productDao) {
        this.productDao = productDao;
    }

    public int insertProduct(Products product) {
        try {
            productDao.save(product);
            logger.info("insertProduct processed correctly");
            return 1;
        } catch (Exception e) {
            logger.error("Error "+e.getMessage());
            return 0;
        }
    }

    public int updateProduc(Products product) {
        try {
            productDao.save(product);
            logger.info("updateProduc processed correctly");
            return 1;
        } catch (Exception e) {
            logger.error("Error "+e.getMessage());
            return 0;
        }
    }

    public int deleteProduct (Long id){
        try {
            productDao.deleteById(id);
            logger.info("deleteProduct processed correctly");
            return 1;
        } catch (Exception e) {
            logger.error("Error "+e.getMessage());
            return 0;
        }
    }

    public List<Products> getAllProducts() {
        List<Products> products = productDao.findAll();
        logger.info("getAllProducts processed correctly");
        return products;
    }
}
