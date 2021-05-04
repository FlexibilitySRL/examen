package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.datasource.model.BaseModel;
import ar.com.plug.examen.datasource.model.Purchase;
import ar.com.plug.examen.datasource.repo.PurchaseRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProcessPurchaseServiceImplTest {

    public static final ObjectMapper MAPPER = new ObjectMapper();
    ProcessPurchaseServiceImpl purchaseService;
    private PurchaseRepo purchaseRepo;

    @BeforeEach
    void before() {
        purchaseRepo = Mockito.mock(PurchaseRepo.class);
        purchaseService = new ProcessPurchaseServiceImpl(purchaseRepo);
    }

    @Test
    void approve() {
        //setup
        final long id = 777L;
        final Purchase build = Purchase.builder().id(777L).build();
        when(purchaseRepo.findById(id)).thenReturn(Optional.of(build));
        when(purchaseRepo.save(build)).thenReturn(build);

        //execute
        purchaseService.approve(id);

        //validate
        assertTrue(build.isApproved());
        Mockito.verify(purchaseRepo, Mockito.times(1)).findById(id);
        Mockito.verify(purchaseRepo, Mockito.times(1)).save(build);
    }

    @Test
    void findAllByCreationDateTimeBetween() throws ParseException {
        //setup
        final String startDateString = "2021-04-01";
        final String endDateString = "2021-04-10";
        final Date startDate = ProcessPurchaseServiceImpl.simpleDateFormat.parse(startDateString);
        final Date endDate = ProcessPurchaseServiceImpl.simpleDateFormat.parse(endDateString);
        final List<Purchase> expected = Collections.singletonList(Purchase.builder().id(888L).build());
        when(purchaseRepo.findAllByCreationDateTimeBetween(startDate, endDate)).thenReturn(expected);

        //execute
        final List<Purchase> allByCreationDateTimeBetween = purchaseService.findAllByCreationDateTimeBetween(startDateString, endDateString);

        //validate
        Assert.assertEquals(expected, allByCreationDateTimeBetween);
        Mockito.verify(purchaseRepo, Mockito.times(1)).findAllByCreationDateTimeBetween(startDate, endDate);
    }

    @Test
    void findAllByCreationDateTimeBetweenError() {
        //setup
        final String startDateString = "Error";

        //execute
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> purchaseService.findAllByCreationDateTimeBetween(startDateString, null));

        //validate
        assertEquals("Invalid dates format must be yyyy-MM-dd but was start: Error, end: null", exception.getMessage());
    }


    @Test
    void getDomainClass() {
        Assert.assertEquals(Purchase.class, purchaseService.getDomainClass());
    }

    @Test
    void create() throws IOException {
        //setup
        final ObjectNode root = MAPPER.createObjectNode();
        final Purchase toSave = Purchase.builder().build();
        final Purchase expected = Purchase.builder().id(887L).build();
        when(purchaseRepo.save(toSave)).thenReturn(expected);

        //execute
        final Purchase purchase = purchaseService.createOrUpdate(root);

        //validate
        Assert.assertEquals(expected, purchase);
        Mockito.verify(purchaseRepo, Mockito.times(0)).findById(any());
        Mockito.verify(purchaseRepo, Mockito.times(1)).save(toSave);
    }

    @Test
    void createError() {
        //setup
        class TestError extends BaseModel {
            TestError() throws InstantiationException {
                throw new InstantiationException("testError");
            }
        }
        AbstractBaseModelService<JpaRepository<TestError, Long>, TestError> abstractBaseModelService = new AbstractBaseModelService<JpaRepository<TestError, Long>, TestError>(null) {
            @Override
            Class<TestError> getDomainClass() {
                return TestError.class;
            }
        };

        //execute
        Exception exception = assertThrows(IllegalArgumentException.class, () -> abstractBaseModelService.createOrUpdate(MAPPER.createObjectNode()));

        //validate
        assertEquals("TestError could not createOrUpdate entity with json: {}", exception.getMessage());

    }

    @Test
    void update() throws IOException {
        //setup
        final long id = 887L;
        final ObjectNode root = MAPPER.createObjectNode();
        root.put("id", 887L);
        root.put("approved", true);
        final Purchase expected = Purchase.builder().id(id).build();
        when(purchaseRepo.findById(id)).thenReturn(Optional.of(expected));
        when(purchaseRepo.save(expected)).thenReturn(expected);

        //execute
        final Purchase purchase = purchaseService.createOrUpdate(root);

        //validate
        Assert.assertEquals(expected, purchase);
        Assert.assertTrue(purchase.isApproved());
        Mockito.verify(purchaseRepo, Mockito.times(1)).findById(id);
        Mockito.verify(purchaseRepo, Mockito.times(1)).save(expected);
    }

    @Test
    void read() {
        //setup
        final long id = 887L;
        final Purchase expected = Purchase.builder().id(id).build();
        when(purchaseRepo.findById(id)).thenReturn(Optional.of(expected));

        //execute
        final Purchase purchase = purchaseService.read(id);

        //validate
        Assert.assertEquals(expected, purchase);
        Mockito.verify(purchaseRepo, Mockito.times(1)).findById(id);
        Mockito.verify(purchaseRepo, Mockito.times(0)).save(expected);
    }

    @Test
    void delete() {
        //setup
        final long id = 887L;
        final Purchase expected = Purchase.builder().id(id).build();
        when(purchaseRepo.findById(id)).thenReturn(Optional.of(expected));
        when(purchaseRepo.save(expected)).thenReturn(expected);

        //execute
        final Purchase purchase = purchaseService.delete(id);

        //validate
        Assert.assertEquals(expected, purchase);
        Assert.assertNotNull(purchase.getDeleted());
        Mockito.verify(purchaseRepo, Mockito.times(1)).findById(id);
        Mockito.verify(purchaseRepo, Mockito.times(1)).save(expected);
    }
}