package ar.com.plug.examen.domain.service.serviceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import ar.com.plug.examen.creator.ProductCreator;
import ar.com.plug.examen.domain.model.entities.Product;
import ar.com.plug.examen.domain.model.jpa.ProductJpaDao;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import ar.com.plug.examen.dto.ProductDto;
import ar.com.plug.examen.mapper.ProductMapper;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductServiceTest {

	@InjectMocks
	private ProductServiceImpl service;
	
	@Mock
	private ProductMapper mapper;
	
	@Mock
	private ProductJpaDao jpa;
	
	@Test
	public void createProductTest() {
		
		ProductDto pMock = ProductCreator.createProductDtoWithId((long)1);
		Product product = ProductCreator.createProductWithNameAndDescription("Product1","Product1");
		ProductDto pToSave = ProductCreator.createProductDtoWithNameAndDescription("Product1","Product1");
		
		when(this.mapper.entityToDto(this.jpa.save(product))).thenReturn(pMock);

		ProductDto response = this.service.save(pToSave);
		assertEquals(pMock, response);
		verify(this.jpa, times(1)).save(product);
		
	}
	
	
	@Test
	public void findAllProductTest() {
		List<ProductDto> pList = ProductCreator.createProductDtoList(ProductCreator
				.createProductDtoWithNameAndDescription("Product1","Product1"), 
				ProductCreator.createProductDtoWithNameAndDescription("Product2","Product2"));

		when(this.mapper.entityListToDtoList(jpa.findAll())).thenReturn(pList);

		List<ProductDto> response = this.service.findAll();
		assertEquals(pList, response);
		verify(this.jpa, times(2)).findAll();
	}
	
	
	@Test
	public void findProductByIdTest() throws Exception {
		Product p = ProductCreator.createProductWithId((long)1);
		
		when(this.jpa.findById((long)1)).thenReturn(Optional.of(p));

		ProductDto response = this.service.findById((long)1);
		assertEquals(mapper.entityToDto(p), response);
		verify(this.jpa, times(1)).findById((long)1);
	}
	
	@Test
	public void deleteProductTest() {

		ProductDto pMock = ProductCreator.createProductDtoWithId((long)1);
		Product product = ProductCreator.createProductWithId((long)1);
		
		when(this.mapper.entityToDto(this.jpa.save(product))).thenReturn(pMock);

		service.delete((long)1);
		
		assertFalse(service.findAll().contains(pMock));
		verify(this.jpa, times(1)).deleteById((long)1);
	}
}

