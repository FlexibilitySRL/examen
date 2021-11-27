package ar.com.plug.examen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.app.fixtures.SellerFixture;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.exceptions.GenericBadRequestException;
import ar.com.plug.examen.domain.exceptions.GenericNotFoundException;
import ar.com.plug.examen.domain.mappers.SellerMapper;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.service.impl.SellerServiceImpl;
import ar.com.plug.examen.domain.validators.Validator;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.Silent.class)
public class SellerServiceTest {

    @InjectMocks
    private SellerServiceImpl sellerService;

    @Mock
    private SellerRepository sellerRepository;

    @Mock
    private SellerMapper sellerMapper;

    @Mock
    private Validator validator;

    @Test
    public void findAllTest() {
        List<SellerApi> listSellers = SellerFixture.getSellerApitList(SellerFixture.getSellerApi(), SellerFixture.getSellerApi());
        when(this.sellerMapper.sellersToListSellerApi(this.sellerRepository.findAll()))
                .thenReturn(listSellers);

        List<SellerApi> response = this.sellerService.findAll();
        assertEquals(listSellers, response);
        verify(this.sellerRepository, times(2)).findAll();
    }

    @Test
    public void findByIdCheckedTest() {
        Seller seller = SellerFixture.getSellerWithId(1L);
        when(this.sellerRepository.findById(1L)).thenReturn(Optional.of(seller));

        SellerApi response = this.sellerService.findByIdChecked(1L);
        assertEquals(this.sellerMapper.sellerToSellerApi(seller), response);
        verify(this.sellerRepository, times(1)).findById(1L);
    }

    @Test
    public void findByIdChecked_NotFoundTest() {
        when(this.sellerRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(GenericNotFoundException.class, () -> {
            this.sellerService.findByIdChecked(1L);
        });

        assertTrue(exception.getMessage().contains("Seller not found"));
        verify(this.sellerRepository, times(1)).findById(1L);
    }

    @Test
    public void save_successTest() {
        SellerApi sellerMock = SellerFixture.getSellerApiWithId(1L);
        Seller seller = SellerFixture.getSeller();
        SellerApi sellerToSave = SellerFixture.getSellerApi();
        when(this.sellerMapper.sellerToSellerApi(this.sellerRepository.save(seller))).thenReturn(sellerMock);
        doNothing().when(this.validator).validateSeller(sellerToSave, Boolean.FALSE);

        SellerApi response = this.sellerService.save(sellerToSave);
        assertEquals(sellerMock, response);
        verify(this.sellerRepository, times(1)).save(seller);
    }

    @Test
    public void save_badRequestTest() {
        SellerApi sellerMock = SellerFixture.getSellerApiWithId(1L);
        sellerMock.setUserName(null);
        Seller seller = SellerFixture.getSeller();
        GenericBadRequestException badRequestException = new GenericBadRequestException("The username is required");
        when(this.sellerMapper.sellerToSellerApi(this.sellerRepository.save(seller))).thenReturn(sellerMock);
        doThrow(badRequestException).when(this.validator).validateSeller(any(), any());

        Exception exception = assertThrows(GenericBadRequestException.class, () -> {
            this.sellerService.save(sellerMock);
        });

        assertTrue(exception.getMessage().contains("The username is required"));
        verify(this.sellerRepository, times(1)).save(seller);
    }

    @Test
    public void update_successTest() {
        SellerApi sellerMock = SellerFixture.getSellerApiWithId(1L);
        Seller seller = SellerFixture.getSellerWithId(1L);
        SellerApi sellerToSave = SellerFixture.getSellerApiWithId(1L);
        when(this.sellerMapper.sellerToSellerApi(this.sellerRepository.save(seller))).thenReturn(sellerMock);
        doNothing().when(this.validator).validateSeller(sellerToSave, Boolean.TRUE);
        when(this.sellerRepository.findById(1L)).thenReturn(Optional.of(seller));

        SellerApi response = this.sellerService.update(sellerToSave);
        assertEquals(sellerMock, response);
        verify(this.sellerRepository, times(1)).save(seller);
    }

    @Test
    public void update_badRequestTest() {
        SellerApi sellerMock = SellerFixture.getSellerApiWithId(1L);
        Seller seller = SellerFixture.getSellerWithId(1L);
        GenericBadRequestException badRequestException = new GenericBadRequestException("The id is required");
        when(this.sellerMapper.sellerToSellerApi(this.sellerRepository.save(seller))).thenReturn(sellerMock);
        when(this.sellerRepository.findById(1L)).thenReturn(Optional.of(seller));
        doThrow(badRequestException).when(this.validator).validateSeller(any(), any());

        Exception exception = assertThrows(GenericBadRequestException.class, () -> {
            this.sellerService.update(sellerMock);
        });

        assertTrue(exception.getMessage().contains("The id is required"));
        verify(this.sellerRepository, times(1)).save(seller);
    }

    @Test
    public void update_notFoundTest() {
        SellerApi sellerMock = SellerFixture.getSellerApiWithId(1L);
        Seller seller = SellerFixture.getSellerWithId(1L);
        GenericNotFoundException genericNotFoundException = new GenericNotFoundException("The id is required");
        when(this.sellerMapper.sellerToSellerApi(this.sellerRepository.save(seller))).thenReturn(sellerMock);
        when(this.sellerRepository.findById(1L)).thenReturn(Optional.of(seller));
        doThrow(genericNotFoundException).when(this.validator).validateSeller(any(), any());

        Exception exception = assertThrows(GenericNotFoundException.class, () -> {
            this.sellerService.update(sellerMock);
        });

        assertTrue(exception.getMessage().contains("The id is required"));
        verify(this.sellerRepository, times(1)).save(seller);
    }

    @Test
    public void deleteTest() {
        SellerApi sellerMock = SellerFixture.getSellerApiWithId(1L);
        Seller seller = SellerFixture.getSellerWithId(1L);
        when(this.sellerMapper.sellerToSellerApi(this.sellerRepository.save(seller))).thenReturn(sellerMock);
        when(this.sellerRepository.findById(1L)).thenReturn(Optional.of(seller));

        this.sellerService.delete(1L);

        assertFalse(this.sellerService.findAll().contains(sellerMock));
        verify(this.sellerRepository, times(1)).deleteById(1L);
    }

    @Test
    public void deleteTest_notFound() {
        GenericNotFoundException genericNotFoundException = new GenericNotFoundException("Seller not found");
        doThrow(genericNotFoundException).when(this.sellerRepository).findById(1L);

        Exception exception = assertThrows(GenericNotFoundException.class, () -> {
            this.sellerService.delete(1L);
        });

        assertTrue(exception.getMessage().contains("Seller not found"));
        verify(this.sellerRepository, times(0)).deleteById(1L);
    }
}
