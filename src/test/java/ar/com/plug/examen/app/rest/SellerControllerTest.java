package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.domain.dto.SellerDTO;
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
public class SellerControllerTest
{
    private SellerDTO dto;
    private static String BASE_PATH = "/seller/";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    @BeforeAll
    public void setDTO()
    {
        dto = DTOFactory.createSellerDTO();
        dto.setFullName( "Integration Test Name" );
        dto.setContactEmail( "integration@test.com" );
    }

    @Test
    @Order( 1 )
    public void saveSellerTest()
    {
        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<SellerDTO> request = new HttpEntity<>( dto, headers );
        final ResponseEntity<SellerDTO> response =
                restTemplate.postForEntity( BASE_PATH + "register", request, SellerDTO.class );

        assertSame( response.getStatusCode(), HttpStatus.OK );
        assertEquals( response.getBody().getContactEmail(), dto.getContactEmail() );
        assertEquals( response.getBody().getFullName(), dto.getFullName() );
    }

    @Test
    @Order( 2 )
    public void findAllSellerTest()
    {
        final ResponseEntity<List> response = restTemplate.getForEntity( BASE_PATH + "all", List.class );
        assertSame( response.getStatusCode(), HttpStatus.OK );
        assertFalse( Objects.requireNonNull( response.getBody() ).isEmpty() );
        assertSame( response.getBody().size(), 2 );

        final SellerDTO previouslyRegistered = mapper.convertValue( response.getBody().get( 1 ), SellerDTO.class );
        assertEquals( previouslyRegistered.getFullName(), dto.getFullName() );
        assertEquals( previouslyRegistered.getContactEmail(), dto.getContactEmail() );

        dto.setId( previouslyRegistered.getId() );
    }

    @Test
    @Order( 3 )
    public void findSellerByIdTest()
    {
        final ResponseEntity<SellerDTO> response =
                restTemplate.getForEntity( BASE_PATH + "profile/" + dto.getId(), SellerDTO.class );
        assertSame( response.getStatusCode(), HttpStatus.OK );

        final SellerDTO previouslyRegistered = mapper.convertValue( response.getBody(), SellerDTO.class );
        assertEquals( previouslyRegistered.getFullName(), dto.getFullName() );
        assertEquals( previouslyRegistered.getContactEmail(), dto.getContactEmail() );
    }

    @Test
    @Order( 4 )
    public void updateSellerTest()
    {
        dto.setFullName( "Integration Test Name UPDATE" );
        dto.setContactEmail( "integrationUPDATE@test.com" );

        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<SellerDTO> request = new HttpEntity<>( dto, headers );
        final ResponseEntity<SellerDTO> response =
                restTemplate.exchange( BASE_PATH + "update", HttpMethod.PUT, request, SellerDTO.class );

        final SellerDTO previouslyRegistered = mapper.convertValue( response.getBody(), SellerDTO.class );
        assertEquals( previouslyRegistered.getFullName(), dto.getFullName() );
        assertEquals( previouslyRegistered.getContactEmail(), dto.getContactEmail() );
    }

    @Test
    @Order( 5 )
    public void deleteSellerTest()
    {
        restTemplate.delete( BASE_PATH + "delete/" + dto.getId() );

        final ResponseEntity<SellerDTO> response =
                restTemplate.getForEntity( BASE_PATH + "profile/" + dto.getId(), SellerDTO.class );
        assertSame( response.getStatusCode(), HttpStatus.NOT_FOUND );
    }
}
