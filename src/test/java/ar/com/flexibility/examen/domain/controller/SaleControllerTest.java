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

import ar.com.flexibility.examen.app.api.SaleApi;
import ar.com.flexibility.examen.config.MessagesProps;
import ar.com.flexibility.examen.domain.enu.SaleStatus;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles ("test")
public class SaleControllerTest {
	
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
            .get("/sale")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
            .andExpect(status().isOk());
    }
    
    @Test
    public void getListByStatus() throws Exception
    {
    	// Arrange
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .get("/sale/status/{status}", SaleStatus.PENDIENTE)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
            .andExpect(status().isOk());
    }
    
    @Test
    public void getSaleNotFound() throws Exception
    {
    	// Arrange
    	String code = "SALE06";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .get("/sale/{code}", code)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
    		.andExpect(MockMvcResultMatchers.jsonPath("$").value(this.messages.getSaleNotFound()))
            .andExpect(status().isInternalServerError());
    }
    
    @Test
    public void getSaleSuccess() throws Exception
    {
    	// Arrange
    	String code = "SALE01";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .get("/sale/{code}", code)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
        	.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(code))
            .andExpect(status().isOk());
    }
    
    @Test
    public void saveSaleCodeExists() throws Exception
    {
    	// Arrange
    	String code = "SALE01";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .post("/sale")
            .content(objectMapper.writeValueAsString(this.buildRequest(code)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
    		.andExpect(MockMvcResultMatchers.jsonPath("$").value(this.messages.getSaleDuplicated()))
            .andExpect(status().isInternalServerError());
    }
    
    @Test
    public void saveSaleSuccess() throws Exception
    {
    	// Arrange
    	String code = "SALE99";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .post("/sale")
            .content(objectMapper.writeValueAsString(this.buildRequest(code)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
			.andExpect(MockMvcResultMatchers.jsonPath("$").value(this.messages.getSucessTransaction()))
			.andExpect(status().isOk());
    }
    
    @Test
    public void updateSaleAlreadyApproved() throws Exception
    {
    	// Arrange
    	String code = "SALE03";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .put("/sale/{code}", code)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
        	.andExpect(MockMvcResultMatchers.jsonPath("$").value(this.messages.getSaleAlreadyApproved()))
            .andExpect(status().isInternalServerError());
    }
    
    @Test
    public void updateSaleSuccess() throws Exception
    {
    	// Arrange
    	String code = "SALE01";
        
        // Action
        mvc.perform( MockMvcRequestBuilders
            .put("/sale/{code}", code)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
    		// Assert
			.andExpect(MockMvcResultMatchers.jsonPath("$").value(this.messages.getSucessTransaction()))
            .andExpect(status().isOk());
    }
    
    private SaleApi buildRequest (String code) {
    	SaleApi request = new SaleApi();
    	request.setClientIdentifier("123456");
    	request.setCode(code);
    	request.setProductAmount(40);
    	request.setProductCode("DELL01");
    	request.setSellerIdentifier("123456");
    	
    	return request;
    }
    
}
