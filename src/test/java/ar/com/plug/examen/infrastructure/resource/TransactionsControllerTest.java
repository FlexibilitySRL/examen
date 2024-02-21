package ar.com.plug.examen.infrastructure.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import ar.com.plug.examen.TestUtil;
import ar.com.plug.examen.infrastructure.rest.resource.TransactionsController;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TransactionsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createTransacctionsOK() throws Exception {
        mockMvc
                .perform(post(TransactionsController.PATH.concat(TransactionsController.CREATE))
                        .content(TestUtil.asJsonString(TestUtil.buildTransaction(TestUtil.ID_1, 1)))
                        .contentType("application/json"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void findByClientEmailOK() throws Exception {

        mockMvc
                .perform(get(TransactionsController.PATH.concat(TransactionsController.GET_BY_CLIENT_EMAIL))
                        .param("email", TestUtil.EMAIL_2)
                        .contentType("application/json"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void findByApprovedOK() throws Exception {
        mockMvc
                .perform(get(TransactionsController.PATH.concat(TransactionsController.GET_ALL))
                        .param("approved", "false")
                        .contentType("application/json"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void approvedTransacctionsOK() throws Exception {

        mockMvc
                .perform(post(TransactionsController.PATH.concat(TransactionsController.APPROVED))
                        .content(TestUtil.asJsonString(Arrays.asList(1, 2)))
                        .contentType("application/json"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
}
