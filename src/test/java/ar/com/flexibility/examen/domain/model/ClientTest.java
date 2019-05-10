package ar.com.flexibility.examen.domain.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.flexibility.examen.app.api.ClientApi;

@RunWith(MockitoJUnitRunner.class)
public class ClientTest
{
	
	@Test
	public void testClientEquals()
	{
		//given
		Client c1 = null;
		Client c2 = null;
		Client c3 = null;
		Client c4 = null;
		Client c5 = null;
		ClientApi cApi = new ClientApi();
		cApi.setId(3L);
		cApi.setFullname("full name client");
		cApi.setEmail("email client");
		
		//when
		c1 = new Client();
		c2 = c1;
		c3 = new Client(cApi);
		c4 = new Client();
		c4.setId(cApi.getId());
		c4.setFullname(cApi.getFullname());
		c4.setEmail(cApi.getEmail());
		c5 = new Client(cApi);
		c5.setId(null);
		
		//then
		assert(!c1.equals(null));
		
		assert(c1.equals(c2));
		assert(c1.hashCode() == c2.hashCode());
		
		assert(!c3.equals(cApi));
		assert(c3.hashCode() == cApi.hashCode());
		
		assert(c3.equals(new Client(cApi)));
		assert(c3.hashCode() == new Client(cApi).hashCode());
		
		assert(c4.equals(new Client(cApi)));
		assert(c4.hashCode() == new Client(cApi).hashCode());
		
		assert(c3.equals(c4));
		assert(c3.hashCode() == c4.hashCode());
		
		assert(!c5.equals(new Client(cApi)));
		assert(c5.hashCode() != new Client(cApi).hashCode());
	}

}
