package ar.com.flexibility.examen.domain.service.it;

import ar.com.flexibility.examen.app.api.VendorApi;
import ar.com.flexibility.examen.domain.exceptions.NotFoundException;
import ar.com.flexibility.examen.domain.model.Vendor;
import ar.com.flexibility.examen.domain.service.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VendorServiceTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private VendorService vendorService;
    private Vendor vendor;
    private VendorApi vendorApiNew;
    private VendorApi vendorApiUpdated;

    @Before
    public void setUp() {
        vendor = new Vendor();
        vendor.setName("Mexx");
        vendor = entityManager.merge(vendor);
        vendorApiNew = new VendorApi();
        vendorApiNew.setName("Compu Mundo");
        vendorApiUpdated = new VendorApi(vendor.getId(), "Falabella");
    }

    @Test
    public void shouldGetAllVendors() {
        List<VendorApi> vendorList = vendorService.all();
        assertFalse(vendorList.isEmpty());
    }

    @Test
    public void shouldGetAVendor() {
        VendorApi vendorApi = vendorService.get(vendor.getId());
        assertNotNull(vendorApi);
        assertEquals(vendor.getId(), vendorApi.getId());
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundWhenGettingAVendor() {
        vendorService.get(-1L);
    }

    @Test
    public void shouldCreateNewVendor() {
        VendorApi created = vendorService.create(vendorApiNew);
        assertNotNull(created);
        assertNotNull(created.getId());
    }

    @Test
    public void shouldUpdateVendor() {
        VendorApi updated = vendorService.update(vendor.getId(), vendorApiUpdated);
        assertNotNull(updated);
        assertEquals(vendorApiUpdated.getId(), updated.getId());
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundWhenUpdatingAVendor() {
        vendorService.update(-1L, vendorApiNew);
    }

    @Test(expected = NotFoundException.class)
    public void shouldRemoveVendor() {
        vendorService.remove(vendorApiUpdated.getId());
        vendorService.get(vendorApiUpdated.getId());
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundRemovingANotFoundVendor() {
        vendorService.remove(-1L);
    }
}
