package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Message;
import ar.com.flexibility.examen.domain.service.impl.ProcessMessageServiceImpl;
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
        final String messageTest = "TEST";
        final Message message = messageService.processMessage(messageTest);

        assertNotNull(message);
        assertEquals(message.getMessage(), messageTest);
    }
}
