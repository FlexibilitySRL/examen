package ar.com.plug.examen.domain.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.plug.examen.app.rest.ProductController;

@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@AutoConfigureRestDocs
public class ProductControllerDocumentedTest {
	@InjectMocks
	ProductController productController;

	@Mock
	ProductService productService;
	
    @Autowired
    private ObjectMapper objectMapper;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");	
	
	
	@Autowired
	private MockMvc mockMvc;	
	
//	@Test
//	public void testNewProduct() {
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//		
//		Product product = new Product(44, "Producto 1", "Descripcion producto 1", (float) 1000.1, null);
//		ResponseEntity<?> responseEntity = productController.newProduct(product);
//
//		assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
//		// assertEquals(responseEntity.getHeaders().getLocation().getPath(), "/1");
//	}

	@Test
	public void testOne() throws Exception {
		//Product product = new Product(44, "Producto 1", "Descripcion producto 1", (float) 1000.1, null);
		
		this.mockMvc.perform(get("/payments/products")).andExpect(status().isOk());
		
//		
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//		long id = 1;
//		ResponseEntity<?> responseEntity = productController.one(id);
//
//		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

//	@Test
//	public void testAll() {
//		//Product product = new Product(44, "Producto 1", "Descripcion producto 1", (float) 1000.1, null);
//
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//		ResponseEntity<?> responseEntity = productController.all();
//
//		/*
//		 * assertThat(result.getEmployeeList().size()).isEqualTo(2);
//		 * assertThat(result.getEmployeeList().get(0).getFirstName()).isEqualTo(
//		 * employee1.getFirstName());
//		 */
//
//		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//	}
//
//	@Test
//	public void testUpdate() {
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//		Product product = new Product(44, "Producto 1", "Descripcion producto 1", (float) 1000.1, null);
//		ResponseEntity<?> responseEntity = productController.update(product);
//
//		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//		// assertEquals(responseEntity.getHeaders().getLocation().getPath(), "/1");
//	}
//
//	@Test
//	public void testDelete() {
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//		long id = 1;
//		ResponseEntity<?> responseEntity = productController.delete(id);
//
//		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//	}

}
