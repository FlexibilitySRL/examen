package ar.com.flexibility.examen.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ar.com.flexibility.examen.app.rest.dto.ProductRequestDto;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.impl.ProductServiceImpl;
import ar.com.flexibility.examen.exception.DataValidationException;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
	@InjectMocks
	private ProductServiceImpl productServiceImpl;
	
	@Mock
	private ProductRepository productRepository;

    @Test
    public void updateProduct_throwsException_WhenProductIsNull() {
    	ProductRequestDto dto = new ProductRequestDto();
    	dto.setId(1);
    	dto.setName("name");
    	when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
    	
    	DataValidationException exception = assertThrows(DataValidationException.class, () ->  productServiceImpl.updateProduct(dto));
    	Assertions.assertNotNull(exception.getMessage());
    }
    
    @Test
    public void findProduct() {
    	Product producto = new Product();
    	producto.setId(1);
    	producto.setName("name");
    	when(productRepository.findByName(Mockito.anyString())).thenReturn(producto);
    	
    	Product result = productServiceImpl.findProduct("name");
    	Assertions.assertEquals(producto, result);
    }
    
    @Test
    public void deleteProduct_throwsException_WhenProductNotExist() {
    	ProductRequestDto dto = new ProductRequestDto();
    	dto.setId(1);
    	dto.setName("name");
    	when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
    	
    	DataValidationException exception = assertThrows(DataValidationException.class, () ->  productServiceImpl.deleteProduct(1));
    	Assertions.assertNotNull(exception.getMessage());
    }

}
