package ar.com.plug.examen.service;

import ar.com.plug.examen.dao.ProductDAO;
import ar.com.plug.examen.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductDAO productDao;

    @Autowired
    public ProductService(ProductDAO productDao) {
        this.productDao = productDao;
    }

    public int insertProduct(Products product) {
        try {
            productDao.save(product);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int updateProduc(Products product) {
        try {
            productDao.save(product);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int deleteProduct (Long id){
        try {
            productDao.deleteById(id);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public List<Products> getAllProducts() {
        List<Products> products = productDao.findAll();
        return products;
    }
}
