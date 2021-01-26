package ar.com.plug.examen.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.domain.service.CustomerService;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.domain.service.SellerService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { Application.class })
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ConfigTest {

	@LocalServerPort
	protected int port;

	protected String server = "http://localhost:";

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;
	
	@Autowired
	protected static ProductService productService;

	@Autowired
	protected static CustomerService customerService;

	@Autowired
	protected static SellerService sellerService;
}
