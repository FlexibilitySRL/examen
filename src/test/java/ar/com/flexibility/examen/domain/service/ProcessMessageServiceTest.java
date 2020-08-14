package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Message;
import ar.com.flexibility.examen.domain.service.impl.ProcessMessageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ProcessMessageServiceTest {

    @InjectMocks
    private ProcessMessageServiceImpl messageService;

    @Test
    public void processMessage()
    {
        String messageTest = "TEST";
        Message message = messageService.processMessage(messageTest);

        Assertions.assertNotNull(message);
        Assertions.assertEquals(message.getMessage(), messageTest);
    }
}
