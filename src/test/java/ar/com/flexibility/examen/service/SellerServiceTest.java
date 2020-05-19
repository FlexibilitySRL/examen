package ar.com.flexibility.examen.service;

import ar.com.flexibility.examen.SpringConfig;
import ar.com.flexibility.examen.app.dto.SellerDTO;
import ar.com.flexibility.examen.model.Seller;
import ar.com.flexibility.examen.model.repository.SellerRepository;
import ar.com.flexibility.examen.service.impl.SellerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= SpringConfig.class)
public class SellerServiceTest {

    @Mock
    private SellerRepository sellerRepository;

    @Mock
    private Seller seller;

    @InjectMocks
    private SellerServiceImpl sellerService;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void givenSeller_whenSave_thenGetOk(){
        final SellerDTO sellerDTO = new SellerDTO(1L,"Jose","Santa Fe 2000","jose@gmail.com");

        given(sellerRepository.findById(sellerDTO.getId())).willReturn(Optional.empty());
        given(sellerRepository.save(seller)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        given( modelMapper.map(sellerDTO,Seller.class)).willReturn(seller);

        sellerService.createSeller(sellerDTO);
        Optional<Seller> savedSeller = sellerRepository.findById(1L);

        assertNotNull(savedSeller);
        verify(sellerRepository).save(any(Seller.class));

    }

    @Test
    public void givenSeller_whenUpdate_thenGetOk(){
        final SellerDTO sellerDTO = new SellerDTO(1L,"Jose","Santa Fe 2000","jose@gmail.com");

        given(sellerRepository.save(seller)).willReturn(seller);
        given( modelMapper.map(sellerDTO,Seller.class)).willReturn(seller);

        sellerService.updateSeller(1L, sellerDTO);
        final Optional<Seller> expectedSeller = sellerRepository.findById(1L);

        assertNotNull(expectedSeller);
        verify(sellerRepository).save(any(Seller.class));

    }

    @Test
    public void givenSeller_whenDelete_thenGetOk(){
        final Long sellerId = 1L;

        sellerService.deleteSellerById(sellerId);
        sellerService.deleteSellerById(sellerId);

        verify(sellerRepository, times(2)).deleteById(sellerId);
    }
}
