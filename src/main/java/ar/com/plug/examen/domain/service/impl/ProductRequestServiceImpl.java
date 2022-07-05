package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.ProductDTO;
import ar.com.plug.examen.app.dtoResponse.ProductResponseDTO;
import ar.com.plug.examen.app.dtoResponse.ErrorDTO;
import ar.com.plug.examen.app.dtoResponse.ListProductResponseDTO;
import ar.com.plug.examen.app.entity.ProductEntity;
import ar.com.plug.examen.app.enumeration.ErrorCodeEnumeration;
import ar.com.plug.examen.app.exception.ApiException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.IProductRequestService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProductRequestServiceImpl implements IProductRequestService {

    private final ProductRepository productRepository;
    Log log = LogFactory.getLog(this.getClass());

    @Autowired
    public ProductRequestServiceImpl (final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = false)
    public ProductResponseDTO create(ProductDTO dto) throws IOException {
        try {
            log.info("product - create - " + dto.toString());
            ProductEntity newProduct = ProductEntity.builder()
                    .productCode(dto.getProductCode())
                    .productName(dto.getProductName())
                    .productDescription(dto.getProductDescription())
                    .productPrice(dto.getProductPrice())
                    .build();

            newProduct = productRepository.save(newProduct);

            if (newProduct != null) {
                log.info("product - create - SUCCESS - product_id: " + newProduct.getProductId());
                return ProductResponseDTO.builder()
                        .productId(newProduct.getProductId())
                        .status(HttpStatus.OK.toString())
                        .code("CREATED").build();
            }

            log.error("product - create - ERROR");
            return ProductResponseDTO.builder()
                    .productId(newProduct.getProductId() != null ? newProduct.getProductId() : 0)
                    .status(HttpStatus.BAD_REQUEST.toString())
                    .code("FAILED").build();

        } catch (Exception e) {
            log.error("product - create - ERROR: " + e.getMessage());
            throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INTERNAL_ERROR));
        }
    }

    @Transactional(readOnly = false)
    public ProductResponseDTO update(ProductDTO dto) throws IOException {
        try {
            log.info("purchase - update - " + dto.toString());
            ProductEntity updateProduct = productRepository.findByProductCode(dto.getProductCode());

            if (updateProduct != null) {
                log.info("product - update - PRODUCT EXIST");
                updateProduct.setProductCode(dto.getProductCode());
                updateProduct.setProductName(dto.getProductName());
                updateProduct.setProductDescription(dto.getProductDescription());
                updateProduct.setProductPrice(dto.getProductPrice());

                productRepository.save(updateProduct);

                log.info("purchase - update - SUCCESS - purchase_id: " + updateProduct.getProductId());
                return ProductResponseDTO.builder()
                        .productId(updateProduct.getProductId())
                        .status(HttpStatus.OK.toString())
                        .code("UPDATED").build();
            }

            log.error("purchase - update - PRODUCT NOT EXIST");
            return ProductResponseDTO.builder()
                    .productId(updateProduct.getProductId() != null ? updateProduct.getProductId() : 0)
                    .status(HttpStatus.BAD_REQUEST.toString())
                    .code("FAILED").build();

        } catch (Exception e) {
            log.error("purchase - update - ERROR: " + e.getMessage());
            throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INTERNAL_ERROR));
        }
    }

    @Transactional(readOnly = false)
    public ProductResponseDTO delete(String productCode) throws IOException {
        try {
            log.info("purchase - delete - " + productCode);
            ProductEntity deleteProduct = productRepository.findByProductCode(productCode);

            if (deleteProduct != null) {
                log.info("product - delete - PRODUCT EXIST");
                productRepository.delete(deleteProduct);

                log.info("product - delete - SUCCESS - product_id: " + deleteProduct.getProductId());
                return ProductResponseDTO.builder()
                        .productId(deleteProduct.getProductId())
                        .status(HttpStatus.OK.toString())
                        .code("DELETED").build();
            }

            log.error("product - delete - PRODUCT NOT EXIST");
            return ProductResponseDTO.builder()
                    .productId(deleteProduct.getProductId() != null ? deleteProduct.getProductId() : 0)
                    .status(HttpStatus.BAD_REQUEST.toString())
                    .code("FAILED").build();

        } catch (Exception e) {
            log.error("product - delete - ERROR: " + e.getMessage());
            throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INTERNAL_ERROR));
        }
    }

    public ListProductResponseDTO list() throws IOException {
        try {
            log.info("product - list");
            List<ProductEntity> listProduct = productRepository.findAll();
            ListProductResponseDTO listProducts = new ListProductResponseDTO();
            listProducts.setProducts(new ArrayList<>());

            if (listProduct != null) {
                log.info("product - list - LIST SIZE: " + listProduct.size());
                for (ProductEntity ProductEntity : listProduct) {
                    listProducts.getProducts().add(new Product(
                            ProductEntity.getProductId(),
                            ProductEntity.getProductCode(),
                            ProductEntity.getProductName(),
                            ProductEntity.getProductDescription(),
                            ProductEntity.getProductPrice()
                    ));
                }
                return listProducts;
            }
            log.error("product - list - LIST NOT EXIST");
            return null;
        } catch (Exception e) {
            log.error("product - list - ERROR: " + e.getMessage());
            throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INTERNAL_ERROR));
        }
    }
}