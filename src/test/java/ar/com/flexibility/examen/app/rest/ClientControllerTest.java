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

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.config.MessagesProps;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles ("test")
public class ClientControllerTest {
	
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
            .get("/client")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
            .andExpect(status().isOk());
    }
    
    @Test
    public void getClientNotFound() throws Exception
    {
    	// Arrange
    	String identifier = "123470";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .get("/client/{identifier}", identifier)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
    		.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(this.messages.getClientNotFound()))
            .andExpect(status().isInternalServerError());
    }
    
    @Test
    public void getClientSuccess() throws Exception
    {
    	// Arrange
    	String identifier = "123456";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .get("/client/{identifier}", identifier)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
        	.andExpect(MockMvcResultMatchers.jsonPath("$.identifier").value(identifier))
            .andExpect(status().isOk());
    }
    
    @Test
    public void deleteNotFoundClient() throws Exception
    {
    	// Arrange
    	String identifier = "123480";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .delete("/client/{identifier}", identifier)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
    		.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(this.messages.getClientPurchasesError()))
            .andExpect(status().isInternalServerError());
    }
    
    @Test
    public void deleteClient() throws Exception
    {
    	// Arrange
    	String identifier = "123460";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .delete("/client/{identifier}", identifier)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(this.messages.getSucessTransaction()))
            .andExpect(status().isOk());
    }
    
    @Test
    public void saveClientIdentifierExists() throws Exception
    {
    	// Arrange
    	String identifier = "123456";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .post("/client")
            .content(objectMapper.writeValueAsString(this.buildRequest(identifier)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
    		.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(this.messages.getClientDuplicated()))
            .andExpect(status().isInternalServerError());
    }
    
    @Test
    public void saveClientSuccess() throws Exception
    {
    	// Arrange
    	String identifier = "123499";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .post("/client")
            .content(objectMapper.writeValueAsString(this.buildRequest(identifier)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(this.messages.getSucessTransaction()))
			.andExpect(status().isOk());
    }
    
    @Test
    public void updateClientNewIdentifierExists() throws Exception
    {
    	// Arrange
    	String identifier = "123456";
    	String newIdentifier = "123457";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .put("/client/{identifier}", identifier)
            .content(objectMapper.writeValueAsString(this.buildRequest(newIdentifier)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
        	.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(this.messages.getClientDuplicated()))
            .andExpect(status().isInternalServerError());
    }
    
    @Test
    public void updateClientSuccess() throws Exception
    {
    	// Arrange
    	String identifier = "123458";
    	String newIdentifier = "123461";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .put("/client/{identifier}", identifier)
            .content(objectMapper.writeValueAsString(this.buildRequest(newIdentifier)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(this.messages.getSucessTransaction()))
            .andExpect(status().isOk());
    }
    
    private ClientApi buildRequest (String identifier) {
    	ClientApi request = new ClientApi();
    	request.setIdentifier(identifier);
    	request.setName("New Client");
    	request.setSurname("New Surname");
    	
    	return request;
    }
    
}
