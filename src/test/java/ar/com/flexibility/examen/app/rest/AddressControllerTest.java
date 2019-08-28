package ar.com.flexibility.examen.app.rest;

import java.util.Arrays;
import java.util.List;

import ar.com.flexibility.examen.app.service.impl.AddressServiceImpl;
import ar.com.flexibility.examen.domain.model.Address;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static ar.com.flexibility.examen.utils.Utils.asJsonString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class AddressControllerTest {

	private MockMvc mockMvc;

	private List<Address> addresses;

	private PageRequest pageRequest;

	@Mock
	private AddressServiceImpl addressService;

	@InjectMocks
	private AddressController addressController;

	@Before
	public void setUp() {
		this.mockMvc = standaloneSetup(this.addressController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.build();

		final int pageNumber = 1;
		final int pageSize = 10;
		this.pageRequest = new PageRequest(pageNumber, pageSize);

		Address laPlataAddress = new Address("Argentina", "Buenos Aires", "La Plata", "direction");
		Address buenosAiresAddress = new Address("Argentina", "Buenos Aires", "Buenos Aires", "direction");
		this.addresses = Arrays.asList(laPlataAddress, buenosAiresAddress);
	}

	@Test
	public void getAddressesTest() throws Exception {

		when(addressService.getAddresses(this.pageRequest)).thenReturn(new PageImpl<>(this.addresses));

		this.mockMvc
				.perform(get("/api/addresses")
						.param("page", "1")
						.param("size", "10")
						.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.content", hasSize(2)))
				.andExpect(jsonPath("$.content[0].country", is("Argentina")));
	}

	@Test
	public void getAddresses() throws Exception {

		when(addressService.getAddresses(1L, this.pageRequest)).thenReturn(new PageImpl<>(this.addresses));

		this.mockMvc
				.perform(get("/api/customers/1/addresses")
						.param("page", "1")
						.param("size", "10")
						.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.content", hasSize(2)))
				.andExpect(jsonPath("$.content[0].country", is("Argentina")))
				.andExpect(jsonPath("$.content[1].city", is("Buenos Aires")));
	}

	@Test
	public void getAddress() throws Exception {

		when(addressService.getAddress(1L, 1L)).thenReturn(this.addresses.get(0));

		this.mockMvc
				.perform(get("/api/customers/1/addresses/1")
						.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.country", is("Argentina")))
				.andExpect(jsonPath("$.city", is("La Plata")));
	}

	@Test
	public void createAddress() throws Exception {

		Address address = new Address("Argentina", "Buenos Aires", "Quilmes", "quilmes 500");

		when(addressService.createAddress(1L, address)).thenReturn(address);

		this.mockMvc
				.perform(post("/api/customers/1/addresses")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(address))
				)
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.direction", is("quilmes 500")));

		verify(addressService, times(1)).createAddress(1L, address);

	}

	@Test
	public void updateAddress() throws Exception {

		Address address = new Address("Argentina", "Buenos Aires", "Quilmes", "quilmes 500");

		when(addressService.updateAddress(1L, 1L, address)).thenReturn(address);

		this.mockMvc
				.perform(patch("/api/customers/1/addresses/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(address))
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.direction", is("quilmes 500")));

		verify(addressService, times(1)).updateAddress(1L, 1L, address);

	}

	@Test
	public void deleteAddress() throws Exception {

		String message = "The address with id: " + 1L + " was deleted.";

		when(addressService.deleteAddress(1L, 1L)).thenReturn(message);

		this.mockMvc
				.perform(delete("/api/customers/1/addresses/1")
						.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.message", is(message)));

		verify(addressService, times(1)).deleteAddress(1L, 1L);

	}

}