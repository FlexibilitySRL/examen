package ar.com.plug.examen.domain.service.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.xml.bind.ValidationException;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.SellerDto;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.service.impl.SellerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RunWith(MockitoJUnitRunner.class)
public class SellerServiceImplTest
{
	@InjectMocks
	private SellerServiceImpl service;

	@Mock
	private SellerRepository repository;

	private Seller seller1;
	private Seller seller2;
	private Seller seller3;
	private Seller seller4;
	private Seller seller5;
	private Seller seller6;

	@Before
	public void setup()
	{
		seller1 = Seller.builder()
			.code("SELL-1")
			.document("0-11")
			.description("SELLERTEST1")
			.active(Boolean.TRUE)
			.build();

		seller2 = Seller.builder()
			.code("SELL-2")
			.document("0-22")
			.description("SELLERTEST2")
			.active(Boolean.TRUE)
			.build();

		seller3 = Seller.builder()
			.code("SELL-3")
			.document("0-33")
			.description("SELLERTEST3")
			.active(Boolean.TRUE)
			.build();

		seller4 = Seller.builder()
			.code("SELL-4")
			.document("0-44")
			.description("SELLERTEST4")
			.active(Boolean.TRUE)
			.build();

		seller5 = Seller.builder()
			.code("SELL-5")
			.document("0-55")
			.description("SELLERTEST5")
			.active(Boolean.TRUE)
			.build();

		seller6 = Seller.builder()
			.code("SELL-6")
			.document("0-66")
			.description("SELLERTEST6")
			.active(Boolean.TRUE)
			.build();
	}

	@Test
	public void mockNotNull()
	{
		assertThat(repository).isNotNull();
		assertThat(service).isNotNull();
	}

	@Test
	public void getSellerByIdTest()
	{
		seller1.setId(10L);
		when(repository.findById(10L)).thenReturn(Optional.ofNullable(seller1));
		Seller sellerFromService = service.getSellerById(10L);
		assertThat(sellerFromService.getId()).isEqualTo(seller1.getId());
	}

	@Test(expected = NoSuchElementException.class)
	public void getSellerByIdNullThrowsNoSuchElementExceptionTest()
	{
		service.getSellerById(null);
	}

	@Test
	public void getSellerByCodeTest()
	{
		when(repository.findByCode("SELL-2")).thenReturn(seller2);
		Seller sellerFromService = service.getSellerByCode("SELL-2");
		assertThat(sellerFromService.getId()).isEqualTo(seller2.getId());
	}

	@Test(expected = NoSuchElementException.class)
	public void getSellerCodeNullThrowsNoSuchElementExceptionTest()
	{
		service.getSellerByCode(null);
	}

	@Test
	public void getAllSellersPaginated()
	{
		int pageSize = 2;
		int pageNumber = 0;
		List<Seller> page1Sellers = Arrays.asList(seller1, seller2);
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Seller> sellerPage = new PageImpl<>(page1Sellers);
		when(repository.findAll(pageable)).thenReturn(sellerPage);

		PageDto<Seller> sellerPageTest = service.getAllSellersPageable(pageNumber, pageSize);
		assertThat(sellerPageTest.getContent().size()).isEqualTo(pageSize);
		assertThat(sellerPageTest.getContent().get(0).getDocument()).isEqualTo(seller1.getDocument());
		assertThat(sellerPageTest.getContent().get(1).getDocument()).isEqualTo(seller2.getDocument());
	}

	@Test(expected = ValidationException.class)
	public void saveSellerThrowsExceptionTest() throws ValidationException
	{
		service.saveSeller(null);
	}

	@Test
	public void saveSellerTest() throws ValidationException
	{
		seller1.setId(1L);
		SellerDto dto = new SellerDto(seller1.getCode(), seller1.getDocument(),
			seller1.getDescription(), seller1.getActive());
		when(repository.save(any(Seller.class))).thenReturn(seller1);
		Seller savedSeller = service.saveSeller(dto);
		assertThat(savedSeller).isNotNull();
		assertThat(savedSeller.getDocument()).isEqualTo(dto.getDocument());
	}

	@Test
	public void updateSellerTest() throws ValidationException
	{
		String updatedCode = "updated-code";
		seller1.setId(1L);
		seller1.setCode(updatedCode);

		SellerDto dto = new SellerDto(updatedCode, seller1.getDocument(),
			seller1.getDescription(), seller1.getActive());
		when(repository.findById(1L)).thenReturn(Optional.ofNullable(seller1));
		when(repository.save(any(Seller.class))).thenReturn(seller1);

		Seller updatedSeller = service.updateSeller(1L, dto);
		assertThat(updatedSeller).isNotNull();
		assertThat(updatedSeller.getDocument()).isEqualTo(dto.getDocument());
		assertThat(updatedSeller.getCode()).isEqualTo(updatedCode);
	}

	@Test(expected = NoSuchElementException.class)
	public void updateSellerWrongId() throws ValidationException
	{
		String updatedCode = "updated-code";
		seller1.setId(1L);
		seller1.setCode(updatedCode);

		SellerDto dto = new SellerDto(updatedCode, seller1.getDocument(),
			seller1.getDescription(), seller1.getActive());
		service.updateSeller(1L, dto);
	}

	@Test(expected = ValidationException.class)
	public void updateSellerNullId() throws ValidationException
	{
		service.updateSeller(null, new SellerDto());
	}

	@Test(expected = ValidationException.class)
	public void updateSellerNullDto() throws ValidationException
	{
		service.updateSeller(1L, null);
	}

	@Test(expected = ValidationException.class)
	public void inactiveSellerNullId() throws ValidationException
	{
		service.inactivateSeller(null);
	}

	@Test(expected = NoSuchElementException.class)
	public void inactivateSellerWrongId() throws ValidationException
	{
		service.inactivateSeller(1L);
	}

	@Test
	public void inactivateSellerTest() throws ValidationException
	{
		seller1.setId(1L);
		seller1.setActive(false);
		when(repository.findById(1L)).thenReturn(Optional.ofNullable(seller1));
		when(repository.save(any(Seller.class))).thenReturn(seller1);
		Seller inactiveSeller = service.inactivateSeller(1L);
		assertThat(inactiveSeller).isNotNull();
		assertThat(inactiveSeller.getActive()).isFalse();
		assertThat(inactiveSeller.getId()).isEqualTo(seller1.getId());
	}
}
