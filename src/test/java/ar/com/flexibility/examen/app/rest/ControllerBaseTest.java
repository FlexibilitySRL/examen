package ar.com.flexibility.examen.app.rest;

import java.io.IOException;

import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author <a href="mailto:abeljose13@gmail.com">Avelardo Gavide</a>
 *
 */
public abstract class ControllerBaseTest {
	protected static final String CONTENT_TYPE = "application/json;charset=UTF-8";
	
	protected MockMvc mockMvc;
	protected ObjectMapper mapper;
	
	protected abstract void initMockData();
	
	protected abstract void initMockRequest();
	
	public void setup() {
		MockitoAnnotations.initMocks(this);
		initMockData();
		initMockRequest();
	}
	
	protected String json(Object o) throws IOException {
		return mapper.writeValueAsString(o);
	}
}
