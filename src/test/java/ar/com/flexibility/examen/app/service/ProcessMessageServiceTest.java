package ar.com.flexibility.examen.app.service;

import ar.com.flexibility.examen.domain.model.Message;
import ar.com.flexibility.examen.app.service.impl.ProcessMessageServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ProcessMessageServiceTest {

    @InjectMocks
    private ProcessMessageServiceImpl messageService;

    @Test
    public void processMessage()
    {
        String messageTest = "TEST";
        Message message = messageService.processMessage(messageTest);

        assertNotNull(message);
        assertEquals(message.getMessage(), messageTest);
    }
}
