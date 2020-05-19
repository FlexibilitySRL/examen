package ar.com.flexibility.examen.service;

import ar.com.flexibility.examen.SpringConfig;
import ar.com.flexibility.examen.app.dto.ProductDTO;
import ar.com.flexibility.examen.app.dto.ProductDTO;
import ar.com.flexibility.examen.model.Product;
import ar.com.flexibility.examen.model.Product;
import ar.com.flexibility.examen.model.repository.ProductRepository;
import ar.com.flexibility.examen.model.repository.ProductRepository;
import ar.com.flexibility.examen.service.impl.ProductServiceImpl;
import ar.com.flexibility.examen.service.impl.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= SpringConfig.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private Product product;

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void givenProduct_whenSave_thenGetOk(){
        final ProductDTO productDTO = new ProductDTO(1L,"Sneaker","Air","Nike", 100);

        given(productRepository.findById(productDTO.getId())).willReturn(Optional.empty());
        given(productRepository.save(product)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        given( modelMapper.map(productDTO,Product.class)).willReturn(product);

        productService.createProduct(productDTO);
        Optional<Product> savedProduct = productRepository.findById(1L);

        assertNotNull(savedProduct);
        verify(productRepository).save(any(Product.class));

    }

    @Test
    public void givenProduct_whenUpdate_thenGetOk(){
        final ProductDTO productDTO = new ProductDTO(1L,"Sneaker","Air","Nike", 100);

        given(productRepository.save(product)).willReturn(product);
        given( modelMapper.map(productDTO,Product.class)).willReturn(product);

        productService.updateProduct(1L, productDTO);
        final Optional<Product> expectedProduct = productRepository.findById(1L);

        assertNotNull(expectedProduct);
        verify(productRepository).save(any(Product.class));

    }

    @Test
    public void givenProduct_whenDelete_thenGetOk(){
        final Long productId = 1L;

        productService.deleteProductById(productId);
        productService.deleteProductById(productId);

        verify(productRepository, times(2)).deleteById(productId);
    }
}
