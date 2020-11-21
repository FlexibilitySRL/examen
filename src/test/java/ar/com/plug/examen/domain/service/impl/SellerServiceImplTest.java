package ar.com.plug.examen.domain.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.service.ConverterService;
import ar.com.plug.examen.domain.service.ValidatorsService;

public class SellerServiceImplTest {

	@InjectMocks
	SellerServiceImpl sellerService;
	
	@Mock
	SellerRepository sellerRepository;

	@Mock
	ConverterService converter;

	@Mock
	ValidatorsService validators;

	private Seller seller;
	private SellerApi sellerApi;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		seller = new Seller(1L, "John Doe");
		sellerApi = new SellerApi(1L, "John Doe");
	}
	
	@Test
	void testListAll() throws NotFoundException {
		when(converter.convertList(sellerRepository.findAll(), SellerApi.class)).thenReturn(Arrays.asList(sellerApi));
		List<SellerApi> found = sellerService.listAll();
		assertNotNull(found);
	}
	
	@Test
	void testFindById() throws NotFoundException {
		when(converter.convert(sellerRepository.findOneById(anyLong()), SellerApi.class)).thenReturn(sellerApi);
		SellerApi found = sellerService.findById(1L);
		assertNotNull(found);
		assertEquals(sellerApi, found);
	}

	@Test
	void testFindById_NotFoundException() throws NotFoundException {
		when(converter.convert(sellerRepository.findOneById(anyLong()), SellerApi.class)).thenReturn(null);
		assertThrows(NotFoundException.class, () -> {
			sellerService.findById(1L);
		});
	}

	@Test
	void testFindByName() {
		when(converter.convertList(sellerRepository.findByName(anyString()), SellerApi.class)).thenReturn(Arrays.asList(sellerApi));
		List<SellerApi> sellerApiList = sellerService.findByName("mock name");
		assertNotNull(sellerApiList);
		assertFalse(sellerApiList.isEmpty());
		assertTrue(sellerApiList.contains(sellerApi));
	}

	@Test
	void testSave() throws BadRequestException {
		SellerApi newSeller = new SellerApi("test name");
		when(sellerRepository.save(converter.convert(newSeller, Seller.class))).thenReturn(seller);
		when(converter.convert(seller, SellerApi.class)).thenReturn(sellerApi);
		SellerApi saved = sellerService.save(newSeller);
		assertNotNull(saved);
		assertNull(newSeller.getId());
	}

	@Test
	void testSave_BadRequestException() throws BadRequestException {
		SellerApi newSeller = new SellerApi();
		when(validators.checkCompleteObject(newSeller, true)).thenThrow(BadRequestException.class);
		assertThrows(BadRequestException.class, () -> {
			sellerService.save(newSeller);
		});
	}
	
	@Test
	void testDeleteById() throws NotFoundException, BadRequestException {
		when(sellerRepository.existsById(anyLong())).thenReturn(true);
		sellerService.deleteById(1L);
	}

	@Test
	void testDeleteById_BadRequestException() throws BadRequestException {
		when(validators.checkCompleteObject(seller.getId(), false)).thenThrow(BadRequestException.class);
		assertThrows(BadRequestException.class, () -> {
			sellerService.deleteById(seller.getId());
		});
	}

	@Test
	void testDeleteById_NotFoundException() throws NotFoundException {
		when(sellerRepository.existsById(seller.getId())).thenReturn(false);
		assertThrows(NotFoundException.class, () -> {
			sellerService.deleteById(seller.getId());
		});
	}

	@Test
	void testUpdate() throws NotFoundException, BadRequestException {
		SellerApi newSeller = new SellerApi(1L, "test name");
		when(sellerRepository.existsById(newSeller.getId())).thenReturn(true);
		when(sellerRepository.save(converter.convert(newSeller, Seller.class))).thenReturn(seller);
		when(converter.convert(seller, SellerApi.class)).thenReturn(newSeller);
		SellerApi saved = sellerService.update(newSeller);
		assertNotNull(saved);
		assertNotNull(saved.getId());
		assertEquals(seller.getId(), saved.getId());
		assertNotEquals(seller.getName(), saved.getName());
	}

	@Test
	void testUpdate_BadRequestException() throws BadRequestException {
		when(validators.checkCompleteObject(sellerApi, false)).thenThrow(BadRequestException.class);
		assertThrows(BadRequestException.class, () -> {
			sellerService.update(sellerApi);
		});
	}

	@Test
	void testUpdate_NotFoundException() throws NotFoundException {
		when(sellerRepository.existsById(seller.getId())).thenReturn(false);
		assertThrows(NotFoundException.class, () -> {
			sellerService.update(sellerApi);
		});
	}
}