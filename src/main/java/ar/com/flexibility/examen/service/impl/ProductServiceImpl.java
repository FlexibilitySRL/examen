package ar.com.flexibility.examen.service.impl;

import ar.com.flexibility.examen.app.dto.ProductDTO;
import ar.com.flexibility.examen.model.Product;
import ar.com.flexibility.examen.model.repository.ProductRepository;
import ar.com.flexibility.examen.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createProduct(ProductDTO productDTO) {
        productRepository.save(mapDtoToEntity(productDTO));

    }

    @Override
    public Product retrieveProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            return product.get();
        }
        return null;
    }

    @Override
    public void updateProduct(Long id, ProductDTO productDTO) {
            productDTO.setId(id);
        productRepository.save(mapDtoToEntity(productDTO));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);

    }

    private Product mapDtoToEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO,Product.class);
    }
}
