package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
public class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private final String BASE_URL = "/products/";

    private ProductDTO productDTO;

    private  ProductDTO getProductDTO(){
        ProductDTO producto = new ProductDTO();
        producto.setName("Producto 1");
        producto.setDescription("Descripcion producto 1");
        producto.setPrice(150000D);
        producto.setStock(12);

        return producto;
    }

    @Test
    public void saveProduct() {

        productDTO = getProductDTO();
        Mockito.when(this.productService.save(Mockito.any())).thenReturn(productDTO);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ProductDTO> request = new HttpEntity<>(productDTO, headers);
        ResponseEntity<ProductDTO> response = testRestTemplate.postForEntity(BASE_URL + "save", request, ProductDTO.class);

        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assert.assertTrue(response.getBody().getName().equals(productDTO.getName()));
    }


    @Test
    public void updateProduct() {

        productDTO = getProductDTO();
        Mockito.when(this.productService.update(Mockito.any())).thenReturn(productDTO);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ProductDTO> request = new HttpEntity<>(productDTO, headers);
        ResponseEntity<ProductDTO> response = testRestTemplate.exchange(BASE_URL + "update", HttpMethod.PUT,request, ProductDTO.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertTrue(response.getBody().getName().equals(productDTO.getName()));

    }

    @Test
    public void getProductsById() {

        productDTO = getProductDTO();
        productDTO.setId(5L);
        Mockito.when(this.productService.getById(5L))
                  .thenReturn(Optional.of(productDTO));


        ResponseEntity<ProductDTO> response = testRestTemplate.getForEntity(BASE_URL + productDTO.getId(), ProductDTO.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertTrue(response.getBody().getId() ==  productDTO.getId());

    }

    @Test
    public void findProductByStokGreatherThan() {

        productDTO = getProductDTO();
        Mockito.when(this.productService.findByStokGreatherThan(10))
                .thenReturn(Optional.of(Arrays.asList(productDTO)));

        ResponseEntity<List> response = testRestTemplate.getForEntity(BASE_URL + "stockgreaterthan/"+ 10, List.class);

        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());

    }
    @Test
    public void findProductByPriceIsLessthan() {

        productDTO = getProductDTO();
        Mockito.when(this.productService.findByPriceIsLessthan(160000))
                .thenReturn(Optional.of(Arrays.asList(productDTO)));

        ResponseEntity<List> response = testRestTemplate.getForEntity(BASE_URL + "pricelessthan/"+ 160000, List.class);

        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());

    }

    @Test
    public void findAllProducts() {

        Mockito.when(this.productService.getAll())
                .thenReturn(Arrays.asList(productDTO));

        ResponseEntity<List> response = testRestTemplate.getForEntity(BASE_URL + "all", List.class);

        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }
}

