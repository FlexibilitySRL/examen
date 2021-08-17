package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.domain.dto.DTOFactory;
import ar.com.plug.examen.domain.dto.TransactionDTO;
import ar.com.plug.examen.domain.dto.TransactionDetailDTO;
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
public class TransactionControllerTest
{
    private TransactionDTO dto;
    private TransactionDTO dtoToReject;
    private static String BASE_PATH = "/transaction/";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    @BeforeAll
    public void setDTO()
    {
        dto = DTOFactory.createTransactionDTO();
        dto.setBuyerId( 1 );
        dto.setSellerId( 1 );
        dto.setAmount( 5.05 );
        dto.setPaymentType( DTOFactory.createPaymentTypeDTO( 1 ) );

        final TransactionDetailDTO detailDTO = DTOFactory.createTransactionDetailDTO();

        detailDTO.setProduct( DTOFactory.createProductDTO( 1 ) );
        detailDTO.setQuantity( 1 );
        dto.getDetailList().add( detailDTO );
    }

    @Test
    @Order( 1 )
    public void saveTransactionTest()
    {
        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<TransactionDTO> request = new HttpEntity<>( dto, headers );
        final ResponseEntity<TransactionDTO> response =
                restTemplate.postForEntity( BASE_PATH + "register", request, TransactionDTO.class );

        assertSame( response.getStatusCode(), HttpStatus.OK );
        assertEquals( response.getBody().getBuyerId(), dto.getBuyerId() );
        assertEquals( response.getBody().getSellerId(), dto.getSellerId() );
        assertEquals( response.getBody().getAmount(), dto.getAmount() );
        assertEquals( response.getBody().getAmount(), dto.getAmount() );
        assertEquals( response.getBody().getStatus().getName(), "Pending" );

        assertFalse( Objects.requireNonNull( response.getBody().getDetailList() ).isEmpty() );
        assertSame( response.getBody().getDetailList().size(), 1 );

        final TransactionDetailDTO previouslyRegistered =
                mapper.convertValue( response.getBody().getDetailList().get( 0 ), TransactionDetailDTO.class );
        assertEquals( previouslyRegistered.getProduct().getId(), dto.getDetailList().get( 0 ).getProduct().getId() );
        assertEquals( previouslyRegistered.getQuantity(), dto.getDetailList().get( 0 ).getQuantity() );

        dto.setId( response.getBody().getId() );
    }

    @Test
    @Order( 2 )
    public void findAllTransactionTest()
    {
        final ResponseEntity<List> response = restTemplate.getForEntity( BASE_PATH + "all", List.class );
        assertSame( response.getStatusCode(), HttpStatus.OK );
        assertFalse( Objects.requireNonNull( response.getBody() ).isEmpty() );
        assertSame( response.getBody().size(), 4 );

        final TransactionDTO previouslyRegistered =
                mapper.convertValue( response.getBody().get( 3 ), TransactionDTO.class );
        assertEquals( previouslyRegistered.getId(), dto.getId() );
        assertEquals( previouslyRegistered.getBuyerId(), dto.getBuyerId() );
        assertEquals( previouslyRegistered.getSellerId(), dto.getSellerId() );
        assertEquals( previouslyRegistered.getAmount(), dto.getAmount() );
        assertEquals( previouslyRegistered.getAmount(), dto.getAmount() );
        assertEquals( previouslyRegistered.getStatus().getName(), "Pending" );
    }

    @Test
    @Order( 3 )
    public void findAllTransactionByPaymentTypeCashTest()
    {
        final ResponseEntity<List> response =
                restTemplate.getForEntity( BASE_PATH + "allbypaymenttype/" + 1, List.class );
        assertSame( response.getStatusCode(), HttpStatus.OK );
        assertFalse( Objects.requireNonNull( response.getBody() ).isEmpty() );
        assertSame( response.getBody().size(), 2 );

        final TransactionDTO previouslyRegistered =
                mapper.convertValue( response.getBody().get( 1 ), TransactionDTO.class );
        assertEquals( previouslyRegistered.getId(), dto.getId() );
        assertEquals( previouslyRegistered.getBuyerId(), dto.getBuyerId() );
        assertEquals( previouslyRegistered.getSellerId(), dto.getSellerId() );
        assertEquals( previouslyRegistered.getAmount(), dto.getAmount() );
        assertEquals( previouslyRegistered.getAmount(), dto.getAmount() );
        assertEquals( previouslyRegistered.getStatus().getName(), "Pending" );
    }

    @Test
    @Order( 4 )
    public void findAllTransactionByPaymentTypeCardTest()
    {
        final ResponseEntity<List> response =
                restTemplate.getForEntity( BASE_PATH + "allbypaymenttype/" + 2, List.class );
        assertSame( response.getStatusCode(), HttpStatus.OK );
        assertFalse( Objects.requireNonNull( response.getBody() ).isEmpty() );
        assertSame( response.getBody().size(), 2 );
    }

    @Test
    @Order( 5 )
    public void findAllTransactionByTransactionStatusPendingTest()
    {
        final ResponseEntity<List> response =
                restTemplate.getForEntity( BASE_PATH + "allbytransactionstatus/" + 3, List.class );
        assertSame( response.getStatusCode(), HttpStatus.OK );
        assertFalse( Objects.requireNonNull( response.getBody() ).isEmpty() );
        assertSame( response.getBody().size(), 2 );

        final TransactionDTO previouslyRegistered =
                mapper.convertValue( response.getBody().get( 1 ), TransactionDTO.class );
        assertEquals( previouslyRegistered.getId(), dto.getId() );
        assertEquals( previouslyRegistered.getBuyerId(), dto.getBuyerId() );
        assertEquals( previouslyRegistered.getSellerId(), dto.getSellerId() );
        assertEquals( previouslyRegistered.getAmount(), dto.getAmount() );
        assertEquals( previouslyRegistered.getAmount(), dto.getAmount() );
        assertEquals( previouslyRegistered.getStatus().getName(), "Pending" );

        dtoToReject = mapper.convertValue( response.getBody().get( 0 ), TransactionDTO.class );
    }

    @Test
    @Order( 6 )
    public void approveTransactionTest()
    {
        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<TransactionDTO> request = new HttpEntity<>( headers );
        final ResponseEntity<TransactionDTO> response = restTemplate.exchange( BASE_PATH + "approve/" + dto.getId(),
                                                                        HttpMethod.PUT, request, TransactionDTO.class );

        final TransactionDTO previouslyRegistered =
                mapper.convertValue( response.getBody(), TransactionDTO.class );
        assertEquals( previouslyRegistered.getId(), dto.getId() );
        assertEquals( previouslyRegistered.getBuyerId(), dto.getBuyerId() );
        assertEquals( previouslyRegistered.getSellerId(), dto.getSellerId() );
        assertEquals( previouslyRegistered.getAmount(), dto.getAmount() );
        assertEquals( previouslyRegistered.getAmount(), dto.getAmount() );
        assertEquals( previouslyRegistered.getStatus().getName(), "Approved" );
    }

    @Test
    @Order( 7 )
    public void findAllTransactionByTransactionStatusApprovedTest()
    {
        final ResponseEntity<List> response =
                restTemplate.getForEntity( BASE_PATH + "allbytransactionstatus/" + 1, List.class );
        assertSame( response.getStatusCode(), HttpStatus.OK );
        assertFalse( Objects.requireNonNull( response.getBody() ).isEmpty() );
        assertSame( response.getBody().size(), 2 );

        final TransactionDTO previouslyRegistered =
                mapper.convertValue( response.getBody().get( 1 ), TransactionDTO.class );
        assertEquals( previouslyRegistered.getId(), dto.getId() );
        assertEquals( previouslyRegistered.getBuyerId(), dto.getBuyerId() );
        assertEquals( previouslyRegistered.getSellerId(), dto.getSellerId() );
        assertEquals( previouslyRegistered.getAmount(), dto.getAmount() );
        assertEquals( previouslyRegistered.getAmount(), dto.getAmount() );
        assertEquals( previouslyRegistered.getStatus().getName(), "Approved" );
    }

    @Test
    @Order( 8 )
    public void rejectTransactionTest()
    {
        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<TransactionDTO> request = new HttpEntity<>( headers );
        final ResponseEntity<TransactionDTO> response =
                restTemplate.exchange( BASE_PATH + "reject/" + dtoToReject.getId(),
                                                                       HttpMethod.PUT, request, TransactionDTO.class );

        final TransactionDTO previouslyRegistered =
                mapper.convertValue( response.getBody(), TransactionDTO.class );
        assertEquals( previouslyRegistered.getId(), dtoToReject.getId() );
        assertEquals( previouslyRegistered.getBuyerId(), dtoToReject.getBuyerId() );
        assertEquals( previouslyRegistered.getSellerId(), dtoToReject.getSellerId() );
        assertEquals( previouslyRegistered.getAmount(), dtoToReject.getAmount() );
        assertEquals( previouslyRegistered.getAmount(), dtoToReject.getAmount() );
        assertEquals( previouslyRegistered.getStatus().getName(), "Rejected" );
    }

    @Test
    @Order( 9 )
    public void findAllTransactionByTransactionStatusRejectTest()
    {
        final ResponseEntity<List> response =
                restTemplate.getForEntity( BASE_PATH + "allbytransactionstatus/" + 2, List.class );
        assertSame( response.getStatusCode(), HttpStatus.OK );
        assertFalse( Objects.requireNonNull( response.getBody() ).isEmpty() );
        assertSame( response.getBody().size(), 2 );

        final TransactionDTO previouslyRegistered =
                mapper.convertValue( response.getBody().get( 1 ), TransactionDTO.class );
        assertEquals( previouslyRegistered.getId(), dtoToReject.getId() );
        assertEquals( previouslyRegistered.getBuyerId(), dtoToReject.getBuyerId() );
        assertEquals( previouslyRegistered.getSellerId(), dtoToReject.getSellerId() );
        assertEquals( previouslyRegistered.getAmount(), dtoToReject.getAmount() );
        assertEquals( previouslyRegistered.getAmount(), dtoToReject.getAmount() );
        assertEquals( previouslyRegistered.getStatus().getName(), "Rejected" );
    }

    @Test
    @Order( 10 )
    public void deleteTransactionTest()
    {
        restTemplate.delete( BASE_PATH + "delete/" + dto.getId() );

        final ResponseEntity<List> response = restTemplate.getForEntity( BASE_PATH + "all", List.class );
        assertSame( response.getStatusCode(), HttpStatus.OK );
        assertFalse( Objects.requireNonNull( response.getBody() ).isEmpty() );
        assertSame( response.getBody().size(), 3 );
    }
}
