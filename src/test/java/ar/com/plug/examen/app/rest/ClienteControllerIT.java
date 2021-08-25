package ar.com.plug.examen.app.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.plug.examen.data.repository.ClienteRepository;
import ar.com.plug.examen.domain.dto.ClienteDTO;
import ar.com.plug.examen.domain.mapper.ClienteMapper;

@WebMvcTest(ClienteController.class)
public class ClienteControllerIT {


	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	ClienteMapper clienteMapper;


	@MockBean
	ClienteRepository clienteRepository;
	
	
	
	
	ClienteDTO cliente_1 = new ClienteDTO(0L, "Maria","Mendoza","Av. Los Arboles","1596-58963","maria@correo.com","V1600000");
	ClienteDTO cliente_2 = new ClienteDTO(0L, "Carlos","Ferreira","Av. Las Rosas","1485-0001","carlosfe@correo.com","V1600000");
	ClienteDTO cliente_3 = new ClienteDTO(0L, "Margarita","Valles","Av. El Parque","8946-4061","margarita@correo.com","V1600000");
	
	
	
	
	@Test
	public void postCliente_ShouldReturnCreatedCorrectCliente() throws Exception {
		String jsonExpected = "{\r\n"
				+ "    \"id\": 1,\r\n"
				+ "    \"nombre\": \"Sofia\",\r\n"
				+ "    \"apellido\": \"Ferreira\",\r\n"
				+ "    \"direccion\": \"Peru\",\r\n"
				+ "    \"telefono\": \"1234-6985\",\r\n"
				+ "    \"email\": \"sofiagmail.com\",\r\n"
				+ "    \"numeroIdentificacion\": \"V1000009\"\r\n"
				+ "}";

		mockMvc.perform(post("/payments/clientes/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"id\": 0,\r\n"
						+ "    \"nombre\": \"Sofia\",\r\n"
						+ "    \"apellido\": \"Ferreira\",\r\n"
						+ "    \"direccion\": \"Caracas\",\r\n"
						+ "    \"telefono\": \"1234-6985\",\r\n"
						+ "    \"email\": \"sofiagmail.com\",\r\n"
						+ "    \"numeroIdentificacion\": \"V1000009\"\r\n"
						+ "}"))
		.andExpect(status().isCreated())
		.andExpect(content().string(jsonExpected));
		;
	}
}
