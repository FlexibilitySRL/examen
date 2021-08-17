package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.domain.dto.BuyerDTO;
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
public class BuyerControllerTest
{
    private BuyerDTO dto;
    private static String BASE_PATH = "/buyer/";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    @BeforeAll
    public void setDTO()
    {
        dto = DTOFactory.createBuyerDTO();
        dto.setName( "Integration Test Name" );
        dto.setLastName( "Integration Test LastName" );
        dto.setEmail( "integration@test.com" );
        dto.setDocument( "integrationdocument" );
    }

    @Test
    @Order( 1 )
    public void saveBuyerTest()
    {
        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<BuyerDTO> request = new HttpEntity<>( dto, headers );
        final ResponseEntity<BuyerDTO> response =
                restTemplate.postForEntity( BASE_PATH + "register", request, BuyerDTO.class );

        assertSame( response.getStatusCode(), HttpStatus.OK );
        assertEquals( response.getBody().getName(), dto.getName() );
        assertEquals( response.getBody().getLastName(), dto.getLastName() );
        assertEquals( response.getBody().getEmail(), dto.getEmail() );
        assertEquals( response.getBody().getDocument(), dto.getDocument() );
    }

    @Test
    @Order( 2 )
    public void findAllBuyerTest()
    {
        final ResponseEntity<List> response = restTemplate.getForEntity( BASE_PATH + "all", List.class );
        assertSame( response.getStatusCode(), HttpStatus.OK );
        assertFalse( Objects.requireNonNull( response.getBody() ).isEmpty() );
        assertSame( response.getBody().size(), 2 );

        final BuyerDTO previouslyRegistered = mapper.convertValue( response.getBody().get( 1 ), BuyerDTO.class );
        assertEquals( previouslyRegistered.getName(), dto.getName() );
        assertEquals( previouslyRegistered.getLastName(), dto.getLastName() );
        assertEquals( previouslyRegistered.getEmail(), dto.getEmail() );
        assertEquals( previouslyRegistered.getDocument(), dto.getDocument() );

        dto.setId( previouslyRegistered.getId() );
    }

    @Test
    @Order( 3 )
    public void findBuyerByIdTest()
    {
        final ResponseEntity<BuyerDTO> response =
                restTemplate.getForEntity( BASE_PATH + "profile/" + dto.getId(), BuyerDTO.class );
        assertSame( response.getStatusCode(), HttpStatus.OK );

        final BuyerDTO previouslyRegistered = mapper.convertValue( response.getBody(), BuyerDTO.class );
        assertEquals( previouslyRegistered.getName(), dto.getName() );
        assertEquals( previouslyRegistered.getLastName(), dto.getLastName() );
        assertEquals( previouslyRegistered.getEmail(), dto.getEmail() );
        assertEquals( previouslyRegistered.getDocument(), dto.getDocument() );
    }

    @Test
    @Order( 4 )
    public void updateBuyerTest()
    {
        dto.setName( "Integration Test Name UPDATE" );
        dto.setLastName( "Integration Test LastName UPDATE" );
        dto.setEmail( "integrationUPDATE@test.com" );
        dto.setDocument( "integrationdocument UPDATE" );

        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<BuyerDTO> request = new HttpEntity<>( dto, headers );
        final ResponseEntity<BuyerDTO> response =
                restTemplate.exchange( BASE_PATH + "update", HttpMethod.PUT, request, BuyerDTO.class );

        final BuyerDTO previouslyRegistered = mapper.convertValue( response.getBody(), BuyerDTO.class );
        assertEquals( previouslyRegistered.getName(), dto.getName() );
        assertEquals( previouslyRegistered.getLastName(), dto.getLastName() );
        assertEquals( previouslyRegistered.getEmail(), dto.getEmail() );
        assertEquals( previouslyRegistered.getDocument(), dto.getDocument() );
    }


    @Test
    @Order( 5 )
    public void deleteBuyerTest()
    {
        restTemplate.delete( BASE_PATH + "delete/" + dto.getId() );

        final ResponseEntity<BuyerDTO> response =
                restTemplate.getForEntity( BASE_PATH + "profile/" + dto.getId(), BuyerDTO.class );
        assertSame( response.getStatusCode(), HttpStatus.NOT_FOUND );
    }
}
