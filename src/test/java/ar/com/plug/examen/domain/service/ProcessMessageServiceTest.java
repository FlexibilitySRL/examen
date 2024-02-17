package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Message;
import ar.com.plug.examen.domain.service.impl.ProcessMessageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
public class ProcessMessageServiceTest {

    @InjectMocks
    private ProcessMessageServiceImpl messageService;

    @Test
    public void testProcessMessage()
    {
        String messageTest = "TEST";
        Message message = messageService.processMessage(messageTest);

        assertNotNull(message);
        assertEquals(message.getMessage(), messageTest);
    }
}
