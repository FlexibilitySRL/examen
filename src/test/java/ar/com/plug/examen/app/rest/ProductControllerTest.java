package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.ProductDTO;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
import ar.com.plug.examen.domain.validators.Validator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest
{

	@InjectMocks
	private ProductController productController;

	@Mock
	private ProductServiceImpl mockProductService;

	@Mock
	private Validator mockValidator;

	@Test
	public void testSaveProduct() {
		ProductDTO productDTO = ProductDTO.builder().id(1L).build();

		ResponseEntity<ProductDTO> result = productController.save(productDTO);

		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		verify(mockProductService, times(1)).save(productDTO);
	}

	@Test
	public void testGetAllProducts() {
		List<ProductDTO> productDTOList = Arrays.asList(ProductDTO.builder().id(1L).build(), ProductDTO.builder().id(2L).build());

		when(mockProductService.getAllProducts()).thenReturn(productDTOList);

		ResponseEntity<List<ProductDTO>> allCProducts = productController.getAllProducts();

		assertEquals(HttpStatus.OK, allCProducts.getStatusCode());

		assertEquals(2, allCProducts.getBody().size());
		verify(mockProductService, times(1)).getAllProducts();
	}

	@Test
	public void testGetClientById() {

		ProductDTO productDTO = ProductDTO.builder().id(1L).build();

		when(mockProductService.getProductById(1L)).thenReturn(productDTO);
		ResponseEntity<ProductDTO> result = productController.getProductById(1L);

		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(productDTO, result.getBody());
		verify(mockProductService, times(1))
				.getProductById(1L);
	}

	@Test
	public void testDeleteClient() {

		ResponseEntity<?> delete = productController.delete(1L);

		assertEquals(HttpStatus.OK, delete.getStatusCode());
		verify(mockProductService, times(1)).delete(1L);
	}

}
