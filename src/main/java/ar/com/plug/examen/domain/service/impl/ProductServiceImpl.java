package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.ProductDTO;
import ar.com.plug.examen.domain.converter.ProductConverter;
import ar.com.plug.examen.domain.exception.ProductNotFoundException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.logs.LogAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Implementation of {@link ProductService}
 */
@LogAnnotation
@Service
public class ProductServiceImpl implements ProductService
{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Override
    @Transactional
    public ProductDTO save(ProductDTO productDTO)
    {
        Product product = productRepository.save(productConverter.toModel(productDTO));
        return productConverter.toDTO(product);
    }


    @Override
    public List<ProductDTO> getAllProducts()
    {
        return productRepository.findAll()
              .stream()
              .map(productConverter::toDTO)
              .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO getProductById(Long id)
    {
        return productConverter.toDTO(getProductByIdIfExists(id));
    }

    @Override
    @Transactional
    public ProductDTO update(ProductDTO productDTO)
    {
        getProductByIdIfExists(productDTO.getId());

        return productConverter.toDTO(productRepository.save(productConverter.toModel(productDTO)));
    }

    @Override
    @Transactional
    public void delete(Long id)
    {
        productRepository.delete(getProductByIdIfExists(id));
    }

    /**
     * Get Product by id, if exists return it. If not, throws exception.
     *
     * @param id
     * @return Product
     */
    private Product getProductByIdIfExists(Long id) {

        return productRepository.findById(id)
              .orElseThrow(() -> new ProductNotFoundException("Product with Id "+id+" not found"));
    }

    @Override
    public ProductDTO getProductByIdInStock(Long id, Long quantity)
    {
        Product productByIdIfExists = getProductByIdIfExists(id);

        if (productByIdIfExists.getStock() < quantity) {
            throw new ProductNotFoundException("The product with id "+id+" is not in stock");
        }

        return productConverter.toDTO(productByIdIfExists);
    }
}
