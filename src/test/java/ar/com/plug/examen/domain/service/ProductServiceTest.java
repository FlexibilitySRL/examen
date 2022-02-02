package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.ProductDTO;
import ar.com.plug.examen.domain.converter.ProductConverter;
import ar.com.plug.examen.domain.exception.ProductNotFoundException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest
{
	@InjectMocks
	private ProductServiceImpl productService;

	@Mock
	private ProductRepository mockProductRepository;

	@Mock
	private ProductConverter mockProductConverter;

	@Test
	public void testGetProductById()
	{
		Product product = Product.builder().id(1L).build();
		when(mockProductRepository.findById(1L))
				.thenReturn(Optional.of(product));

		ProductDTO productDTO = productService.getProductById(1L);
		assertEquals(mockProductConverter.toDTO(product), productDTO);
		verify(this.mockProductRepository, times(1)).findById(1L);
	}

	@Test
	public void testGetAllProducts() {
		List<Product> ProductList = Arrays.asList(Product.builder().id(1L).build(),
				Product.builder().id(2L).build());

		when(mockProductRepository.findAll())
				.thenReturn(ProductList);

		List<ProductDTO> response = productService.getAllProducts();
		assertEquals(response.size(), 2);
	}

	@Test
	public void getByIdNotFoundTest() {
		when(mockProductRepository.findById(1L)).thenReturn(Optional.empty());

		Exception exception = assertThrows(ProductNotFoundException.class, () -> {
			productService.getProductById(1L);
		});

		assertTrue(exception.getMessage().contains("Product with Id 1 not found"));
		verify(mockProductRepository, times(1)).findById(1L);
	}

	@Test
	public void saveSuccessTest() {

		ProductDTO expect = ProductDTO.builder().id(1L).build();
		Product product = Product.builder()
				.stock(10L)
				.price(2500.1)
				.name("Chocolate")
				.description("Chocolate with cream")
				.build();

		ProductDTO ProductDTOSave = ProductDTO.builder()
				.stock(10L)
				.price(2500.1)
				.name("Chocolate")
				.description("Chocolate with cream")
				.build();

		when(mockProductConverter.toDTO(this.mockProductRepository.save(product))).thenReturn(expect);

		ProductDTO result = productService.save(ProductDTOSave);

		assertEquals(expect, result);

		verify(mockProductRepository, times(1))
				.save(product);
	}

	@Test
	public void updateTest() {
		ProductDTO expectResult = ProductDTO.builder().id(2L).build();
		Product product = Product.builder().id(2L).build();
		ProductDTO ProductToSave = ProductDTO.builder().id(2L).build();
		when(mockProductConverter.toDTO(this.mockProductRepository.save(product)))
				.thenReturn(expectResult);

		when(mockProductRepository.findById(2L)).thenReturn(Optional.of(product));

		ProductDTO result = productService.update(ProductToSave);
		assertEquals(expectResult, result);
		verify(mockProductRepository, times(1)).save(product);
	}


}
