package ar.com.plug.examen.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ar.com.plug.examen.TestUtil;
import ar.com.plug.examen.domain.Product;
import ar.com.plug.examen.infrastructure.db.repository.ProductEntityRepository;
import ar.com.plug.examen.shared.config.MenssageResponse;
import ar.com.plug.examen.shared.exception.BadRequestException;
import ar.com.plug.examen.shared.exception.ConflictException;
import ar.com.plug.examen.shared.exception.NotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
    @Mock
    private ProductEntityRepository productEntityRepository;
    @Mock
    private MenssageResponse menssageResponse;
    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @Before
    public void setup() {
        when(menssageResponse.getMessages()).thenReturn(TestUtil.buidlMenssageResponse());
    }

    @Test
    public void findByIdTestOk() {
        when(productEntityRepository.findById(TestUtil.ID_1))
                .thenReturn(TestUtil.buidlProductEntity(TestUtil.ID_1));
        Product product = productServiceImpl.findById(TestUtil.ID_1);
        assertEquals(TestUtil.ID_1, product.getId());
    }

    @Test(expected = NotFoundException.class)
    public void findByIdTestNotFoundException() {
        productServiceImpl.findById(TestUtil.ID_1);
    }

    @Test
    public void findAllByFilterOK() {
        int nProduct = 2;
        when(productEntityRepository.findAll()).thenReturn(TestUtil.buidlProductEntityList(nProduct));
        List<Product> products = productServiceImpl.findAllByFilter(TestUtil.buildProductFilter1());
        assertEquals(1, products.size());
    }

    @Test
    public void createOK() {
        when(productEntityRepository.save(any()))
                .thenReturn(TestUtil.buidlProductEntity(TestUtil.ID_1).get());
        Product product = productServiceImpl.create(TestUtil.buildProduct(TestUtil.ID_1));
        assertEquals(TestUtil.ID_1, product.getId());
    }

    @Test(expected = ConflictException.class)
    public void createConflictException() {
        when(productEntityRepository.findByBarCode(any()))
                .thenReturn(TestUtil.buidlProductEntity(TestUtil.ID_1));
        productServiceImpl.create(TestUtil.buildProduct(TestUtil.ID_1));
    }

    @Test
    public void upDateOK() {
        when(productEntityRepository.findById(TestUtil.ID_1))
                .thenReturn(TestUtil.buidlProductEntity(TestUtil.ID_1));
        when(productEntityRepository.save(any()))
                .thenReturn(TestUtil.buidlProductEntity(TestUtil.ID_1).get());
        Product product = productServiceImpl.upDate(TestUtil.buildProduct(TestUtil.ID_1));
        assertEquals(TestUtil.ID_1, product.getId());
    }

    @Test(expected = BadRequestException.class)
    public void upDateBadRequestException() {
        productServiceImpl.upDate(TestUtil.buildProduct(null));
    }

    @Test(expected = NotFoundException.class)
    public void upDateNotFoundException() {
        productServiceImpl.upDate(TestUtil.buildProduct(TestUtil.ID_1));
    }

    @Test
    public void removeOk() {
        when(productEntityRepository.findById(TestUtil.ID_1))
                .thenReturn(TestUtil.buidlProductEntity(TestUtil.ID_1));
        productServiceImpl.remove(TestUtil.ID_1);
    }

    @Test(expected = NotFoundException.class)
    public void removeNotFoundException() {
        productServiceImpl.remove(TestUtil.ID_1);
    }
}
