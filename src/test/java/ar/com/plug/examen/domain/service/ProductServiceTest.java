package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repositories.IProductRepository;
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
import java.math.BigDecimal;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class ProductServiceTest {

    @Mock
    private IProductRepository productRepository;

    @Test
    public void findProduct() {
        Product producto = new Product("Mouse" , "Logitech", "Gamer", new BigDecimal(1000));
        when(productRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(producto));
        Product result = productRepository.findById(1L).get();
        Assertions.assertEquals(producto, result);
    }

    @Test
    public void findAllProducts() {
        Product producto = new Product("Mouse" , "Logitech", "Gamer", new BigDecimal(1000));
        Product producto2 = new Product("Teclado" , "Logitech", "Gamer", new BigDecimal(2000));

        ArrayList<Product> lProducts = new ArrayList<>();
        lProducts.add(producto);
        lProducts.add(producto2);

        when(productRepository.findAll()).thenReturn(lProducts);

        Assertions.assertEquals(productRepository.findAll().spliterator().getExactSizeIfKnown(), 2);
    }

    @Test
    public void saveProduct() {
        Product producto = new Product("Mouse", "Logitech", "Gamer", new BigDecimal(1000));

        when(productRepository.save(Mockito.any())).thenReturn(producto);

        Product savedProduct = productRepository.save(producto);

        Assertions.assertEquals(producto, savedProduct);
    }
}
