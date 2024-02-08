package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Message;
import ar.com.plug.examen.domain.service.impl.ProcessMessageServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcessMessageServiceTest {

    @InjectMocks
    private ProcessMessageServiceImpl messageService;

    @Test
    public void testProcessMessage()
    {
        String messageTest = "TEST";
        Message message = messageService.processMessage(messageTest);

        Assertions.assertNotNull(message);
        assertEquals(message.getMessage(), messageTest);
    }
}
