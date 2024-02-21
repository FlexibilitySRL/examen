package ar.com.plug.examen.infrastructure.resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.jayway.jsonpath.JsonPath;

import ar.com.plug.examen.TestUtil;
import ar.com.plug.examen.infrastructure.rest.resource.ClientController;
import ar.com.plug.examen.shared.config.MenssageResponse;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findByIdOK() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(ClientController.PATH).param("id", TestUtil.ID_1.toString())
                .contentType("application/json"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Integer id = JsonPath.parse(response).read("$.id");
        assertEquals(TestUtil.ID_1, id);
    }

    @Test
    public void createOK() throws Exception {
        mockMvc
                .perform(post(ClientController.PATH)
                        .content(TestUtil.asJsonString(TestUtil.buidlClientNew(TestUtil.ID_11)))
                        .contentType("application/json"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void upDateOk() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(patch(ClientController.PATH)
                        .content(TestUtil.asJsonString(TestUtil.buidlClientUpDate(TestUtil.ID_1)))
                        .contentType("application/json"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Integer id = JsonPath.parse(response).read("$.id");
        assertEquals(TestUtil.ID_1, id);
    }

    @Test
    public void removeOK() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(delete(ClientController.PATH)
                        .param("id", TestUtil.ID_3.toString())
                        .contentType("application/json"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        String code = JsonPath.parse(response).read("$.code");
        assertEquals(MenssageResponse.OK, code);
    }

}
