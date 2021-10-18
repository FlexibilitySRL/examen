package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.IProductRepository;
import ar.com.plug.examen.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public ProductDTO save(ProductDTO productDto) {
        return productRepository.save(productDto);
    }

    @Override
    public ProductDTO update(ProductDTO productDto) {
        return productRepository.save(productDto);
    }

    @Override
    public void delete(long productId) {

       productRepository.delete(productId);
    }

    @Override
    public Optional<ProductDTO> getById(long productId) {
        return productRepository.getById(productId);
    }

    @Override
    public Optional<List<ProductDTO>> findByStokGreatherThan(int stock) {
        return productRepository.findByStokGreatherThan(stock);
    }

    @Override
    public Optional<List<ProductDTO>>  findByPriceIsLessthan(double price) {
        return productRepository.findByPriceIsLessthan(price);
    }

    @Override
    public List<ProductDTO> getAll() {
        return productRepository.getAll();
    }
}
