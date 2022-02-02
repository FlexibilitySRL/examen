package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.SellerDTO;
import ar.com.plug.examen.domain.converter.SellerConverter;
import ar.com.plug.examen.domain.exception.SellerNotFoundException;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.service.impl.SellerServiceImpl;
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
public class SellerServiceTest
{
	@InjectMocks
	private SellerServiceImpl sellerService;

	@Mock
	private SellerRepository mockSellerRepository;

	@Mock
	private SellerConverter mockSellerConverter;

	@Test
	public void testGetSellerById()
	{
		Seller seller = Seller.builder().id(1L).build();
		when(mockSellerRepository.findById(1L))
				.thenReturn(Optional.of(seller));

		SellerDTO sellerDTO = sellerService.getSellerById(1L);
		assertEquals(mockSellerConverter.toDTO(seller), sellerDTO);
		verify(mockSellerRepository, times(1))
				.findById(1L);
	}

	@Test
	public void testGetAllSellers() {
		List<Seller> sellerList = Arrays.asList(Seller.builder().id(1L).build(),
				Seller.builder().id(2L).build());

		when(mockSellerRepository.findAll())
				.thenReturn(sellerList);

		List<SellerDTO> response = sellerService.getAllSellers();
		assertEquals(response.size(), 2);
	}

	@Test
	public void getByIdNotFoundTest() {
		when(mockSellerRepository.findById(1L))
				.thenReturn(Optional.empty());

		Exception exception = assertThrows(SellerNotFoundException.class, () -> {
			sellerService.getSellerById(1L);
		});

		assertTrue(exception.getMessage().contains("Seller with Id 1 not found"));
		verify(mockSellerRepository, times(1)).findById(1L);
	}

	@Test
	public void saveSuccessTest() {
		SellerDTO expect = SellerDTO.builder().id(1L).build();
		Seller seller = Seller.builder()
				.lastName("Boada")
				.firstName("Yeisson")
				.email("yeissonboada@gmail.com")
				.build();

		SellerDTO sellerDTOSave = SellerDTO.builder()
				.lastName("Boada")
				.firstName("Yeisson")
				.email("yeissonboada@gmail.com")
				.build();

		when(mockSellerConverter.toDTO(mockSellerRepository.save(seller)))
				.thenReturn(expect);

		SellerDTO result = sellerService.save(sellerDTOSave);

		assertEquals(expect, result);

		verify(mockSellerRepository, times(1))
				.save(seller);
	}

	@Test
	public void updateTest() {
		SellerDTO expectResult = SellerDTO.builder().id(2L).build();
		Seller seller = Seller.builder().id(2L).build();
		SellerDTO sellerDTOSave = SellerDTO.builder().id(2L).build();
		when(mockSellerConverter.toDTO(mockSellerRepository.save(seller)))
				.thenReturn(expectResult);

		when(mockSellerRepository.findById(2L))
				.thenReturn(Optional.of(seller));

		SellerDTO result = sellerService.update(sellerDTOSave);
		assertEquals(expectResult, result);
		verify(mockSellerRepository, times(1)).save(seller);
	}


}
