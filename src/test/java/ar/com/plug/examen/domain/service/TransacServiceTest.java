package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.*;
import ar.com.plug.examen.domain.repositories.ITransacRepository;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class TransacServiceTest {

    @Mock
    private ITransacRepository transacRepository;

    @Test
    public void findTransac() {
        Set<Item> items = new HashSet<>();
        items.add(new Item(new Product("Mouse" , "Logitech", "Gamer", new BigDecimal(2000)), 1, new BigDecimal(2000), new BigDecimal(2000)));
        Seller seller = new Seller("Nahuel", "Perez");
        Customer customer = new Customer("Nahuel", "Perez", "nahuel.perez@gmail.com");
        Transac transac = new Transac(items, seller, customer, new BigDecimal(2000), false);

        when(transacRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(transac));
        Transac result = transacRepository.findById(1L).get();
        Assertions.assertEquals(transac, result);
    }

    @Test
    public void findAllTransacs() {
        Set<Item> items = new HashSet<>();
        items.add(new Item(new Product("Mouse" , "Logitech", "Gamer", new BigDecimal(2000)), 1, new BigDecimal(2000), new BigDecimal(2000)));
        Seller seller = new Seller("Nahuel", "Perez");
        Customer customer = new Customer("Nahuel", "Perez", "nahuel.perez@gmail.com");
        Transac transac = new Transac(items, seller, customer, new BigDecimal(2000), false);

        Set<Item> items2 = new HashSet<>();
        items.add(new Item(new Product("Teclado" , "Logitech", "Gamer", new BigDecimal(1000)), 1, new BigDecimal(1000), new BigDecimal(1000)));
        Seller seller2 = new Seller("Nahuel", "Perez");
        Customer customer2 = new Customer("Nahuel", "Perez", "nahuel.perez@gmail.com");
        Transac transac2 = new Transac(items2, seller2, customer2, new BigDecimal(1000), false);

        List<Transac> lTransac= new ArrayList<>();
        lTransac.add(transac2);
        lTransac.add(transac2);

        when(transacRepository.findAll()).thenReturn(lTransac);

        Assertions.assertEquals(transacRepository.findAll().spliterator().getExactSizeIfKnown(), 2);
    }

}
