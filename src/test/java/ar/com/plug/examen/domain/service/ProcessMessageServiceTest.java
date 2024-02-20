package ar.com.plug.examen.domain.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import ar.com.plug.examen.shared.domain.model.Message;
import ar.com.plug.examen.shared.domain.service.impl.ProcessMessageServiceImpl;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RunWith(MockitoJUnitRunner.class)
public class ProcessMessageServiceTest {

    @InjectMocks
    private ProcessMessageServiceImpl messageService;

    @Test
    public void testProcessMessage() {
        String messageTest = "TEST";
        Message message = messageService.processMessage(messageTest);

        assertNotNull(message);
        assertEquals(message.getMessage(), messageTest);
    }

}
