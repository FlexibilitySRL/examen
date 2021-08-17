package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.dto.DTOFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@ExtendWith( SpringExtension.class )
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@ContextConfiguration( classes = Application.class )
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
@TestInstance( PER_CLASS )
public class ProductControllerTest
{
    private ProductDTO dto;
    private static String BASE_PATH = "/product/";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    @BeforeAll
    public void setDTO()
    {
        dto = DTOFactory.createProductDTO();
        dto.setName( "Integration Test Name" );
        dto.setDescription( "Integration Test Description" );
        dto.setSellerId( 1 );
        dto.setPrice( 20.20 );
        dto.setStock( 34 );
    }

    @Test
    @Order( 1 )
    public void saveProductTest()
    {
        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<ProductDTO> request = new HttpEntity<>( dto, headers );
        final ResponseEntity<ProductDTO> response =
                restTemplate.postForEntity( BASE_PATH + "register", request, ProductDTO.class );

        assertSame( response.getStatusCode(), HttpStatus.OK );
        assertEquals( response.getBody().getName(), dto.getName() );
        assertEquals( response.getBody().getDescription(), dto.getDescription() );
        assertEquals( response.getBody().getSellerId(), dto.getSellerId() );
        assertEquals( response.getBody().getPrice(), dto.getPrice() );
        assertEquals( response.getBody().getStock(), dto.getStock() );
    }

    @Test
    @Order( 2 )
    public void findAllProductTest()
    {
        final ResponseEntity<List> response = restTemplate.getForEntity( BASE_PATH + "all", List.class );
        assertSame( response.getStatusCode(), HttpStatus.OK );
        assertFalse( Objects.requireNonNull( response.getBody() ).isEmpty() );
        assertSame( response.getBody().size(), 3 );

        final ProductDTO previouslyRegistered = mapper.convertValue( response.getBody().get( 2 ), ProductDTO.class );
        assertEquals( previouslyRegistered.getName(), dto.getName() );
        assertEquals( previouslyRegistered.getDescription(), dto.getDescription() );
        assertEquals( previouslyRegistered.getSellerId(), dto.getSellerId() );
        assertEquals( previouslyRegistered.getPrice(), dto.getPrice() );
        assertEquals( previouslyRegistered.getStock(), dto.getStock() );

        dto.setId( previouslyRegistered.getId() );
    }

    @Test
    @Order( 3 )
    public void findProductByIdTest()
    {
        final ResponseEntity<ProductDTO> response =
                restTemplate.getForEntity( BASE_PATH + "profile/" + dto.getId(), ProductDTO.class );
        assertSame( response.getStatusCode(), HttpStatus.OK );

        final ProductDTO previouslyRegistered = mapper.convertValue( response.getBody(), ProductDTO.class );
        assertEquals( previouslyRegistered.getName(), dto.getName() );
        assertEquals( previouslyRegistered.getDescription(), dto.getDescription() );
        assertEquals( previouslyRegistered.getSellerId(), dto.getSellerId() );
        assertEquals( previouslyRegistered.getPrice(), dto.getPrice() );
        assertEquals( previouslyRegistered.getStock(), dto.getStock() );
    }

    @Test
    @Order( 4 )
    public void updateProductTest()
    {
        dto.setName( "Integration Test Name UPDATE" );
        dto.setDescription( "Integration Test Description UPDATE" );
        dto.setSellerId( 1 );
        dto.setPrice( 34.34 );
        dto.setStock( 20 );

        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<ProductDTO> request = new HttpEntity<>( dto, headers );
        final ResponseEntity<ProductDTO> response =
                restTemplate.exchange( BASE_PATH + "update", HttpMethod.PUT, request, ProductDTO.class );

        final ProductDTO previouslyRegistered = mapper.convertValue( response.getBody(), ProductDTO.class );
        assertEquals( previouslyRegistered.getName(), dto.getName() );
        assertEquals( previouslyRegistered.getDescription(), dto.getDescription() );
        assertEquals( previouslyRegistered.getSellerId(), dto.getSellerId() );
        assertEquals( previouslyRegistered.getPrice(), dto.getPrice() );
        assertEquals( previouslyRegistered.getStock(), dto.getStock() );
    }


    @Test
    @Order( 5 )
    public void deleteProductTest()
    {
        restTemplate.delete( BASE_PATH + "delete/" + dto.getId() );

        final ResponseEntity<ProductDTO> response =
                restTemplate.getForEntity( BASE_PATH + "profile/" + dto.getId(), ProductDTO.class );
        assertSame( response.getStatusCode(), HttpStatus.NOT_FOUND );
    }
}
