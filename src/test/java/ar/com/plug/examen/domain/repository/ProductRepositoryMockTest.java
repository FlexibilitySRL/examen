package ar.com.plug.examen.domain.repository;


import ar.com.plug.examen.domain.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ProductRepositoryMockTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindByCode_theReturnProduct(){
        Product computer = Product.builder()
                .id(1L)
                .code("1234596")
                .name("computer")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .build();
        productRepository.save(computer);
        Product product = productRepository.findByCode(computer.getCode());
        Assertions.assertThat(product).isEqualTo(computer);
    }
}
