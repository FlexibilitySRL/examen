package ar.com.flexibility.examen.app.rest;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.flexibility.examen.Application;
import ar.com.flexibility.examen.app.api.MessageApi;
import ar.com.flexibility.examen.domain.model.Message;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class CustomControllerTest
{

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void testEcho() throws Exception
	{
		// given
		String pathUrl = "/custom/echo";
		Message message = new Message("new message test");
		// when
		MvcResult result = mvc.perform(post(pathUrl).contentType(MediaType.APPLICATION_JSON).content(asJsonString(message)))
				// then
				.andExpect(status().isOk()).andReturn();
		
		// given
		String contentAsString = result.getResponse().getContentAsString();
		// when
		MessageApi messageResponse = new ObjectMapper().readValue(contentAsString.getBytes(), MessageApi.class);
		// then
		assertNotNull(contentAsString);
		assertNotNull(messageResponse.getMessage());
		assertEquals(message.getMessage(), messageResponse.getMessage());
	}
	
	public static String asJsonString(final Object obj)
	{
		try
		{
			return new ObjectMapper().writeValueAsString(obj);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
