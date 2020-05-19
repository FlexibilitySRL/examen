package ar.com.flexibility.examen.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.flexibility.examen.Application;
import ar.com.flexibility.examen.domain.model.Message;
import ar.com.flexibility.examen.domain.service.impl.ProcessMessageServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProcessMessageServiceTest {

	@InjectMocks
	private ProcessMessageServiceImpl messageService;

	@Test
	public void processMessage() {
		String messageTest = "TEST";
		Message message = messageService.processMessage(messageTest);

		assertNotNull(message);
		assertEquals(message.getMessage(), messageTest);
	}
}
