package ar.com.plug.examen.domain.service.integrator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.rest.ProductController;
import ar.com.plug.examen.domain.exception.NotExistException;
import ar.com.plug.examen.domain.exception.ValidatorException;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductControllerTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductController productController;
	
	@BeforeEach
	public void setUp() throws ValidatorException {
		ResponseEntity<String> result = productController.createProduct(new ProductApi(1L,"23","oscar prueba"));
		logger.info(result.getBody());
		
	}
	
	@Test
	public void createProductOK() throws ValidatorException, NotExistException {
		assertThat(productController.createProduct(new ProductApi("23","oscar prueba"))).
		extracting("getBody").
		isEqualTo("Se creo el producto correctamente");
		
	}
	@Test
	public void findProductOK() throws ValidatorException, NotExistException {
	
		assertThat(productController.findProduct(1L)).
		extracting("getBody").
		extracting("getId","getPrice","getName").
		contains(1L,"23","oscar prueba");
	}
	
	@Test
	public void updateProductOK() throws ValidatorException, NotExistException {
		List<ProductApi> products = productController.findProducts().getBody();

		assertThat(productController.updateProduct(new ProductApi(3L,"55","producto modificado"))).
		extracting("getBody").
		isEqualTo("Se modifico el producto correctamente");
		products = productController.findProducts().getBody();

		assertThat(productController.findProduct(3L)).
		extracting("getBody").
		extracting("getId","getPrice","getName").
		contains(3L,"55","producto modificado");
	}
	@Test
	public void deleteProductOK() throws ValidatorException, NotExistException {
		
		assertThat(productController.deleteProduct(2L)).
		extracting("getBody").
		isEqualTo("Se elimino el product correctamente");
		
		assertThatExceptionOfType(NotExistException.class)
		  .isThrownBy(() -> {
			  productController.findProduct(1L);
		}).withMessage("El producto que busca no existe");
		
	}
	
	

}
