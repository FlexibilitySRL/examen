package ar.com.plug.examen.domain.service;

import static org.junit.Assert.*;

import java.util.Objects;

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

    @Test
    public void testProcessMessage2() {
        String valor1 = "hola";
        String valor2 = "Hola";
        String valor3 = null;
        String valor4 = null;
        log.info("A:{}", Objects.equals(valor1, valor2));
        log.info("B:{}", Objects.equals(valor1, valor3));
        log.info("C:{}", Objects.equals(valor3, valor4));
    }
}
