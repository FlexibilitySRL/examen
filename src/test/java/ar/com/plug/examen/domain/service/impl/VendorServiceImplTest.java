package ar.com.plug.examen.domain.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import ar.com.plug.examen.domain.model.Vendor;

@SpringBootTest
@ActiveProfiles("test")
public class VendorServiceImplTest {

	@Autowired
	VendorServiceImpl vendorService;

	@Test
	void given_anExistingPENDINGPurchaseTransaction_when_settingItToRejectedState_then_isRejected() throws Exception {

		Vendor oldEntity = new Vendor();
		oldEntity.setFirstName("stan");
		oldEntity.setLastName("lee");

		oldEntity = vendorService.createVendor(oldEntity);

		Vendor updatedEntity = new Vendor();

		updatedEntity.setFirstName("stan");
		updatedEntity.setLastName("from monkey island");

		updatedEntity = vendorService.updateVendor(oldEntity.getId(), updatedEntity);

		assertNotNull(updatedEntity);
		assertEquals("stan", updatedEntity.getFirstName());
		assertEquals("from monkey island", updatedEntity.getLastName());

	}

}
