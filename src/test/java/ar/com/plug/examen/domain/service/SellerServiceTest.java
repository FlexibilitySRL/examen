package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repositories.ISellerRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class SellerServiceTest {

    @Mock
    private ISellerRepository sellerRepository;

    @Test
    public void findSeller() {
        Seller seller = new Seller("Nahuel", "Perez");
        when(sellerRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(seller));
        Seller result = sellerRepository.findById(1L).get();
        Assertions.assertEquals(seller, result);
    }

    @Test
    public void findAllSellers() {
        Seller seller = new Seller("Nahuel", "Perez");
        Seller seller2 = new Seller("Juan", "Lopez");

        ArrayList<Seller> lSellers = new ArrayList<>();
        lSellers.add(seller);
        lSellers.add(seller2);

        when(sellerRepository.findAll()).thenReturn(lSellers);

        Assertions.assertEquals(sellerRepository.findAll().spliterator().getExactSizeIfKnown(), 2);
    }

    @Test
    public void saveSeller() {
        Seller seller = new Seller("Nahuel", "Perez");

        when(sellerRepository.save(Mockito.any())).thenReturn(seller);

        Seller savedSeller = sellerRepository.save(seller);

        Assertions.assertEquals(seller, savedSeller);
    }
}
