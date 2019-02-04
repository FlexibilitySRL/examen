package ar.com.flexibility.examen.app.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.service.CustomerService;

/**
 * 
 * @author <a href="mailto:abeljose13@gmail.com">Avelardo Gavide</a>
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerControllerTest extends ControllerBaseTest {
	
	private static final String BASE_URL = "/private/customer";
	
	@InjectMocks
	CustomerController customerController;
	
	@Mock
	CustomerService customerService;
	
	private Customer customer;
	
	
	@Before
	public void setup() {
		super.setup();
		
		MockitoAnnotations.initMocks(this);
		mapper = new ObjectMapper();
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}
	
	@Override
	protected void initMockData() {
		// TODO Not implemented yet
	}

	@Override
	protected void initMockRequest() {
		customer = new Customer();
		customer.setCustomerName("Avelardo Gavide");
		customer.setAddress("Parque Patricios");
		customer.setEmail("avelardog@gmail.com");
	}
	
	@Test
	public void givenCustomerEntity_whenCustomControllerPost_thenReturnOK() throws Exception {
		String requestJson = json(customer);
		
		mockMvc.perform(post(BASE_URL)
				.contentType(CONTENT_TYPE)
				.content(requestJson))
				.andDo(print())
				.andExpect(status().isOk());
	}
}
