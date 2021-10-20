package ar.com.plug.examen.domain.repository.impl;

import ar.com.plug.examen.domain.crud.ProductCrudRepository;
import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.mapper.ProductMapper;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements IProductRepository {

    @Autowired
    private ProductCrudRepository productCrudRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDTO save(ProductDTO productDto) {

        Product product = productMapper.toProduct(productDto);
        return productMapper.toProductDto(productCrudRepository.save(product));
    }

    @Override
    public ProductDTO update(ProductDTO productDto) {

        Product product = productMapper.toProduct(productDto);
        return productMapper.toProductDto(productCrudRepository.save(product));
    }

    @Override
    public void delete(long productId) {

        productCrudRepository.deleteById(productId);
    }

    @Override
    public Optional<ProductDTO> getById(long productId) {

        return productCrudRepository.findById(productId)
                .map(product -> productMapper.toProductDto(product));
    }

    @Override
    public Optional<List<ProductDTO>> findByStokGreatherThan(int stock) {
        return productCrudRepository.findByStockIsGreaterThan(stock)
                .map(products -> productMapper.toListProductDto(products));
    }

    @Override
    public Optional<List<ProductDTO>>  findByPriceIsLessthan(double price) {
        return productCrudRepository.findByPriceIsLessThan(price)
                .map(products -> productMapper.toListProductDto(products));
    }

    @Override
    public List<ProductDTO> getAll() {
        List<Product> products = (List<Product>) productCrudRepository.findAll();
        return productMapper.toListProductDto(products);
    }
}
