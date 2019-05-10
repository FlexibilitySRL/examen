package ar.com.flexibility.examen.app.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.flexibility.examen.domain.model.Client;

@RunWith(MockitoJUnitRunner.class)
public class ClientApiTest
{

	@Test
	public void testClientApi()
	{
		//given
		ClientApi cApi1 = null;
		ClientApi cApi2 = null;
		ClientApi cApi3 = null;
		ClientApi cApi4 = null;
		ClientApi cApi5 = null;
		Client client = new Client();
		client.setId(3L);
		client.setFullname("full name client api");
		client.setEmail("email client api");
		
		//when
		cApi1 = new ClientApi();
		cApi2 = cApi1;
		cApi3 = new ClientApi(client);
		cApi4 = new ClientApi();
		cApi4.setId(client.getId());
		cApi4.setFullname(client.getFullname());
		cApi4.setEmail(client.getEmail());
		cApi5 = new ClientApi(client);
		cApi5.setId(null);
		
		//then
		assert(!cApi1.equals(null));
		
		assert(cApi1.equals(cApi2));
		assert(cApi1.hashCode() == cApi2.hashCode());
		
		assert(!cApi3.equals(client));
		assert(cApi3.hashCode() == client.hashCode());
		
		assert(cApi3.equals(new ClientApi(client)));
		assert(cApi3.hashCode() == new ClientApi(client).hashCode());
		
		assert(cApi4.equals(new ClientApi(client)));
		assert(cApi4.hashCode() == new ClientApi(client).hashCode());
		
		assert(cApi3.equals(cApi4));
		assert(cApi3.hashCode() == cApi4.hashCode());
		
		assert(!cApi5.equals(new ClientApi(client)));
		assert(cApi5.hashCode() != new ClientApi(client).hashCode());
	}
}
