package ar.com.plug.examen.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.exception.NotExistException;
import ar.com.plug.examen.domain.exception.ValidatorException;
import ar.com.plug.examen.domain.mapper.Mapper;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;
@RunWith(PowerMockRunner.class)
@PrepareForTest({Mapper.class})
public class ProductServiceImplTest {

	
    private ProductServiceImpl productService;
    @Mock
	private ProductRepository productRepository;
 
	
	@Before
	public void setUp() throws ValidatorException {
		PowerMockito.mockStatic(Mapper.class);
		Optional<Product> optional = Optional.of(new Product(1L,3,"",LocalDateTime.now()));

		Mockito.when(Mapper.mapperToProduct(Mockito.any())).thenReturn(new Product(1L,3,"",LocalDateTime.now()));
		Mockito.when(Mapper.mapperToProductApi(Mockito.any())).thenReturn(new ProductApi(1L,"3",""));

		Mockito.when(productRepository.save(Mockito.any())).thenReturn(new Product(1L,3,"",LocalDateTime.now()) );
		Mockito.when(productRepository.findById(1L)).thenReturn(optional);
		Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(new Product(1L,3,"",LocalDateTime.of(2020, 10, 20, 12, 12))));


		Mockito.doNothing().when(productRepository).delete(Mockito.any());
		productService = new ProductServiceImpl(productRepository);
	}
	@Test
	public void createError() {
		assertThatExceptionOfType(ValidatorException.class)
		  .isThrownBy(() -> {
			  productService.create(new ProductApi(1L,"12",""));
		}).withMessage("[[El campo name es obligatorio]]");
		
	}
	@Test
	public void createOk() throws ValidatorException {
		
		productService.create(new ProductApi(1L,"12","gfgd"));
		
	}
	@Test
	public void updateError() throws ValidatorException {
		assertThatExceptionOfType(ValidatorException.class)
		  .isThrownBy(() -> {
			  productService.update(new ProductApi(1L,"","Producto 1"));
		}).withMessage("[[El campo price es obligatorio]]");
				
	}
	@Test
	public void updateOk() throws ValidatorException, NotExistException {
		
		productService.update(new ProductApi(1L,"12","gfgd"));
		
	}
	@Test
	public void deleteOk() throws NotExistException, ValidatorException {
		productService.delete(1L);
	}
	@Test
	public void deleteError() throws NotExistException {
		assertThatExceptionOfType(NotExistException.class)
		  .isThrownBy(() -> {
			  productService.delete(2L);
		}).withMessage("El producto que quiere eliminar no existe");
			
	}
	@Test
	public void findOk() throws NotExistException {
		assertThat(productService.find(1L)).
		extracting("getId","getPrice","getName").
		contains(1L,"3","");
	}
	@Test
	public void findNull() throws NotExistException {
		assertThatExceptionOfType(NotExistException.class)
		  .isThrownBy(() -> {
			  productService.find(2L);
		}).withMessage("El producto que busca no existe");
	}
	@Test
	public void findAllOk() throws NotExistException {
		
		assertThat(productService.findAll().get(0)).
		extracting("getId","getPrice","getName").
		contains(1L,"3","");
	}
	@Test
	public void findAllNull() throws NotExistException {
		Mockito.when(productRepository.findAll()).thenReturn(null);

		assertThatExceptionOfType(NotExistException.class)
		  .isThrownBy(() -> {
			  productService.findAll();
		}).withMessage("No hay productos cargados");
	}
	
}
