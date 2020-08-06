package ar.com.flexibility.examen.app.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.config.MessagesProps;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles ("test")
public class ProductControllerTest {
	
	@Autowired
	private MessagesProps messages;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getList() throws Exception
    {
    	// Arrange
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .get("/product")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
            .andExpect(status().isOk());
    }
    
    @Test
    public void getProductNotFound() throws Exception
    {
    	// Arrange
    	String code = "LEN010";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .get("/product/{code}", code)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
    		.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(this.messages.getProductNotFound()))
            .andExpect(status().isInternalServerError());
    }
    
    @Test
    public void getProductSuccess() throws Exception
    {
    	// Arrange
    	String code = "DELL01";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .get("/product/{code}", code)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
        	.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(code))
            .andExpect(status().isOk());
    }
    
    @Test
    public void deleteNotFoundProduct() throws Exception
    {
    	// Arrange
    	String code = "LEN010";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .delete("/product/{code}", code)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
    		.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(this.messages.getProductSalesError()))
            .andExpect(status().isInternalServerError());
    }
    
    @Test
    public void deleteProduct() throws Exception
    {
    	// Arrange
    	String code = "TOSH02";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .delete("/product/{code}", code)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(this.messages.getSucessTransaction()))
            .andExpect(status().isOk());
    }
    
    @Test
    public void saveProductCodeExists() throws Exception
    {
    	// Arrange
    	String code = "DELL01";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .post("/product")
            .content(objectMapper.writeValueAsString(this.buildRequest(code)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
    		.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(this.messages.getProductDuplicated()))
            .andExpect(status().isInternalServerError());
    }
    
    @Test
    public void saveProductSuccess() throws Exception
    {
    	// Arrange
    	String code = "LEN019";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .post("/product")
            .content(objectMapper.writeValueAsString(this.buildRequest(code)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(this.messages.getSucessTransaction()))
			.andExpect(status().isOk());
    }
    
    @Test
    public void updateProductNewCodeExists() throws Exception
    {
    	// Arrange
    	String code = "LEN01";
    	String newCode = "MAC01";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .put("/product/{code}", code)
            .content(objectMapper.writeValueAsString(this.buildRequest(newCode)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
        	.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(this.messages.getProductDuplicated()))
            .andExpect(status().isInternalServerError());
    }
    
    @Test
    public void updateProductSuccess() throws Exception
    {
    	// Arrange
    	String code = "LEN01";
    	String newCode = "MAC019";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .put("/product/{code}", code)
            .content(objectMapper.writeValueAsString(this.buildRequest(newCode)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(this.messages.getSucessTransaction()))
            .andExpect(status().isOk());
    }
    
    private ProductApi buildRequest (String code) {
    	ProductApi request = new ProductApi();
    	request.setAmount(10);
    	request.setCode(code);
    	request.setName("New Product");
    	request.setPrice(47.50);
    	
    	return request;
    }
    
}
