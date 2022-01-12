package ar.com.plug.examen.domain.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ar.com.plug.examen.app.exception.BadResourceException;
import ar.com.plug.examen.app.exception.ResourceNotFoundException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductServiceTest {

	@Mock
	private ProductRepository repository;

	@InjectMocks
	private ProductServiceImpl service;
	
	static final Product entity = new Product();
	
	static {
		entity.setId(123L);
		entity.setName("my test product");
		entity.setDescription("my product description");
		entity.setPrice(BigDecimal.ONE);
		entity.setStock(NumberUtils.LONG_ONE);
	}

	@Test
	public void testSave() {

		when(repository.save(any())).thenReturn(entity);

		Product newEntity = service.save(entity);

		assertNotNull(newEntity);
		assertNotNull(newEntity.getId());
	}
	
	@Test
	public void testSaveBadResource() {
		when(repository.save(any())).thenThrow(new RuntimeException());
		assertThatExceptionOfType(BadResourceException.class).isThrownBy(() -> service.save(entity));
	}
	
	@Test
	public void testUpdate() {
		when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
		when(repository.save(any())).thenReturn(entity);

		Product updateEntity = service.update(entity);

		assertNotNull(updateEntity);
		assertNotNull(updateEntity.getId());
	}
	
	@Test
	public void testFindById() {
		when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
		Product find = service.findById(123L);
		assertNotNull(find);

	}
	
	@Test
	public void testFindAll() {
		Page<Product> page = mock(Page.class);
		when(repository.findAll(any(Pageable.class))).thenReturn(page);

		Page<Product> list = service.findAll(1, 2000);

		assertNotNull(list);
	}
	
	
	@Test
	public void testDelete() {
		when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
		service.deleteById(123L);
	}

	@Test
	public void testFindByIdBadRequest() {		
		assertThatExceptionOfType(BadResourceException.class).isThrownBy(() -> service.findById(null));
	}
	
	@Test
	public void testFindByIdNotFound() {
		when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
		assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> service.findById(123L));
	}


}
