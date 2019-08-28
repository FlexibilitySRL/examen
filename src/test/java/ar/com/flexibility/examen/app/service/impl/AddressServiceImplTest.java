package ar.com.flexibility.examen.app.service.impl;

import java.util.Arrays;
import java.util.List;

import ar.com.flexibility.examen.domain.model.Address;
import ar.com.flexibility.examen.domain.repository.AddressRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceImplTest {

	@Mock
	AddressRepository addressRepository;

	@InjectMocks
	AddressServiceImpl addressService;

	@Test
	public void getAddresses() {

		Address address = new Address("Argentina", "Buenos Aires", "La Plata", "direction");
		List<Address> addresses = Arrays.asList(address);

		Pageable pageable = new PageRequest(0, 10);
		when(addressRepository.findAll(pageable)).thenReturn(new PageImpl<>(addresses));

		Page<Address> pagedAddresses = addressService.getAddresses(pageable);

		assertEquals(address, pagedAddresses.getContent().get(0));
		assertEquals(1, pagedAddresses.getTotalPages());
		assertEquals(1, pagedAddresses.getTotalElements());
	}

}