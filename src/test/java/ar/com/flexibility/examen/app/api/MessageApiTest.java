package ar.com.flexibility.examen.app.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class MessageApiTest
{

	@Test
	public void testMessageApi()
	{
		//given
		MessageApi mApi1 = null;
		MessageApi mApi2 = null;
		String message1 = "message 1";
		String message2 = "message 2";
		
		//when
		mApi1 = new MessageApi(message1);
		mApi2 = new MessageApi();
		mApi2.setMessage(message2);
		
		//then
		assertNotNull(mApi1);
		assertNotNull(mApi2);
		assertEquals(mApi1.getMessage(), message1);
		assertEquals(mApi2.getMessage(), message2);
	}
}
