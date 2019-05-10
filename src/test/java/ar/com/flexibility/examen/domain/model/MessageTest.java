package ar.com.flexibility.examen.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MessageTest
{

	@Test
	public void testMessage()
	{
		//given
		Message m1 = null;
		Message m2 = null;
		String message1 = "message 1";
		String message2 = "message 2";
		
		//when
		m1 = new Message(message1);
		m2 = new Message();
		m2.setMessage(message2);
		
		//then
		assertNotNull(m1);
		assertNotNull(m2);
		assertEquals(m1.getMessage(), message1);
		assertEquals(m2.getMessage(), message2);
	}
	
}
