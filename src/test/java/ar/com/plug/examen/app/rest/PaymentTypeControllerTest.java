package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.domain.dto.PaymentTypeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
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
public class PaymentTypeControllerTest
{
    private static String BASE_PATH = "/paymenttype/";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @Order( 1 )
    public void findAllPaymentTypeTest()
    {
        final ResponseEntity<List> response = restTemplate.getForEntity( BASE_PATH + "all", List.class );
        assertSame( response.getStatusCode(), HttpStatus.OK );
        assertFalse( Objects.requireNonNull( response.getBody() ).isEmpty() );
        assertSame( response.getBody().size(), 2 );

        final PaymentTypeDTO card = mapper.convertValue( response.getBody().get( 1 ), PaymentTypeDTO.class );
        assertEquals( card.getName(), "Card" );
        final PaymentTypeDTO cash = mapper.convertValue( response.getBody().get( 0 ), PaymentTypeDTO.class );
        assertEquals( cash.getName(), "Cash" );
    }
}
