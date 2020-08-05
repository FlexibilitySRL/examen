package ar.com.flexibility.examen.domain.controller;

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

import ar.com.flexibility.examen.app.api.SellerApi;
import ar.com.flexibility.examen.config.MessagesProps;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles ("test")
public class SellerControllerTest {
	
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
            .get("/seller")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
            .andExpect(status().isOk());
    }
    
    @Test
    public void getSellerNotFound() throws Exception
    {
    	// Arrange
    	String identifier = "123470";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .get("/seller/{identifier}", identifier)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
    		.andExpect(MockMvcResultMatchers.jsonPath("$").value(this.messages.getSellerNotFound()))
            .andExpect(status().isInternalServerError());
    }
    
    @Test
    public void getSellerSuccess() throws Exception
    {
    	// Arrange
    	String identifier = "123456";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .get("/seller/{identifier}", identifier)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
        	.andExpect(MockMvcResultMatchers.jsonPath("$.identifier").value(identifier))
            .andExpect(status().isOk());
    }
    
    @Test
    public void deleteNotFoundSeller() throws Exception
    {
    	// Arrange
    	String identifier = "123480";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .delete("/seller/{identifier}", identifier)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
    		.andExpect(MockMvcResultMatchers.jsonPath("$").value(this.messages.getSellerNotFound()))
            .andExpect(status().isInternalServerError());
    }
    
    @Test
    public void deleteSeller() throws Exception
    {
    	// Arrange
    	String identifier = "123460";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .delete("/seller/{identifier}", identifier)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
			.andExpect(MockMvcResultMatchers.jsonPath("$").value(this.messages.getSucessTransaction()))
            .andExpect(status().isOk());
    }
    
    @Test
    public void saveSellerIdentifierExists() throws Exception
    {
    	// Arrange
    	String identifier = "123456";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .post("/seller")
            .content(objectMapper.writeValueAsString(this.buildRequest(identifier)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
    		.andExpect(MockMvcResultMatchers.jsonPath("$").value(this.messages.getSellerDuplicated()))
            .andExpect(status().isInternalServerError());
    }
    
    @Test
    public void saveSellerSuccess() throws Exception
    {
    	// Arrange
    	String identifier = "123499";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .post("/seller")
            .content(objectMapper.writeValueAsString(this.buildRequest(identifier)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
			.andExpect(MockMvcResultMatchers.jsonPath("$").value(this.messages.getSucessTransaction()))
			.andExpect(status().isOk());
    }
    
    @Test
    public void updateSellerNewIdentifierExists() throws Exception
    {
    	// Arrange
    	String identifier = "123456";
    	String newIdentifier = "123457";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .put("/seller/{identifier}", identifier)
            .content(objectMapper.writeValueAsString(this.buildRequest(newIdentifier)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
        	.andExpect(MockMvcResultMatchers.jsonPath("$").value(this.messages.getSellerDuplicated()))
            .andExpect(status().isInternalServerError());
    }
    
    @Test
    public void updateSellerSuccess() throws Exception
    {
    	// Arrange
    	String identifier = "123458";
    	String newIdentifier = "123461";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .put("/seller/{identifier}", identifier)
            .content(objectMapper.writeValueAsString(this.buildRequest(newIdentifier)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
			.andExpect(MockMvcResultMatchers.jsonPath("$").value(this.messages.getSucessTransaction()))
            .andExpect(status().isOk());
    }
    
    private SellerApi buildRequest (String identifier) {
    	SellerApi request = new SellerApi();
    	request.setIdentifier(identifier);
    	request.setName("New Seller");
    	request.setSurname("New Surname");
    	
    	return request;
    }
    
}
