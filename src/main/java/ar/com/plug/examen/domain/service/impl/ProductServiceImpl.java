package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private  ProductRepository productRepository;

    @Override
    public void createProduct(ProductDTO productDTO) {
        Product productToSave = new Product();
        productToSave.setDescriptionProduct(productDTO.getDescriptionProduct());
        productToSave.setCategory(productDTO.getCategory());
        productToSave.setPrice(productDTO.getPrice());
        productRepository.save(productToSave);
    }

    @Override
    public void deleteProduct(Long idProduct) {
        productRepository.deleteById(idProduct);
    }

    @Override
    public void editProduct(Long idProduct, ProductDTO productDTO) {
        Optional<Product> productResult = productRepository.findById(idProduct);
        productResult.get().setDescriptionProduct(productDTO.getDescriptionProduct());
        productResult.get().setCategory(productDTO.getCategory());
        productResult.get().setPrice(productDTO.getPrice());
        productRepository.save(productResult.get());
    }
}
