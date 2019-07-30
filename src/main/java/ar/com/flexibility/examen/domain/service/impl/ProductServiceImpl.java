package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private Logger logger = LoggerFactory.getLogger("ar.com.flexibility.examen.domain.service.impl.ProductServiceImpl");
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        Product savedProduct = productRepository.save(product);

        checkServiceStatus(product, "The product was saved successfully", "An error ocurred while saving the product");

        return savedProduct;
    }

    private void checkServiceStatus(Product product, String infoMessage, String warningMessage) {
        if (product != null) {
            logger.info(infoMessage);
        } else {
            logger.warn(warningMessage);
        }
    }

    @Override
    public Product updateProduct(Product product) {
        Product updatedProduct = productRepository.save(product);

        checkServiceStatus(updatedProduct, "The product was updated successfully", "An error ocurred while updating the product");

        return updatedProduct;
    }

    @Override
    public Product findById(Long id) {
        Product searchedProduct = productRepository.findOne(id);

        checkServiceStatus(searchedProduct, "The product was searched successfully", "An error ocurred while searching the product");

        return searchedProduct;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.delete(id);

        Product searchedProduct = productRepository.findOne(id);

        checkSuccessfullyDelete(searchedProduct);
    }

    private void checkSuccessfullyDelete(Product product) {
        if (product == null) {
            logger.info("The shopping list was deleted successfully");
        } else {
            logger.warn("An error ocurred while deleting the shopping list");
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> allProducts = (List<Product>) productRepository.findAll();

        checkSuccessfullyFindAll(allProducts);

        return allProducts;
    }

    private void checkSuccessfullyFindAll(List<Product> allProducts) {
        if (allProducts != null) {
            logger.info("The shopping lists was found successfully");
        } else {
            logger.warn("An error ocurred while searching the shopping lists");
        }
    }


}
