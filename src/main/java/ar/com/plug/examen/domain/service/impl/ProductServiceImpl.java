package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.ProductModel;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AGB
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public ProductModel saveProduct(ProductModel product) {
        return repository.save(product);
    }

    @Override
    public List<ProductModel> saveProducts(List<ProductModel> products) {
        return repository.saveAll(products);
    }

    @Override
    public List<ProductModel> getProducts() {
        return repository.findAll();
    }

    @Override
    public ProductModel getProductById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ProductModel getProductByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public String deleteProduct(int id) {
        repository.deleteById(id);
        return ("Product Id: " + id + " Removed");

    }

    @Override
    public ProductModel updateProduct(ProductModel product) {
        //Get existingProduct from db
        ProductModel existingProduct = repository.findById(product.getId()).orElse(null);

        //Update audit model
        existingProduct.setUpdateDt(product.getUpdateDt());
        existingProduct.setUpdateBy(product.getUpdateBy());

        //Update class atributes
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setSku(product.getSku());

        return repository.save(existingProduct);
    }

}
