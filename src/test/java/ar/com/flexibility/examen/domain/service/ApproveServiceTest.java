package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.dto.ApproveDTO;
import ar.com.flexibility.examen.domain.model.Approve;
import ar.com.flexibility.examen.domain.repository.ApproveRepository;
import ar.com.flexibility.examen.domain.service.impl.ApproveServiceImpl;
import ar.com.flexibility.examen.domain.service.impl.TransactionPurchasesServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApproveServiceTest {
    @InjectMocks
    private ApproveServiceImpl approveService;

    @Mock
    private ApproveRepository approveRepository;

    @Mock
    private TransactionPurchasesServiceImpl transactionPurchasesService;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void approvedTransaction(){
        String transactionId= "AAAAAA34FGDDD45";
        ApproveDTO approveDTO = approveDefault();

        when(transactionPurchasesService.existTransactionId(anyString())).thenReturn(true);
        when(approveRepository.findByTransactionId(anyString())).thenReturn(Optional.empty());
        when(modelMapper.map(any(), any())).thenReturn(approveDTO);

        ApproveDTO approve =  approveService.approve(transactionId);

        assertNotNull(approve);
        assertEquals(transactionId, approve.getTransactionId());
    }

    public ApproveDTO approveDefault(){
        ApproveDTO dto = new ApproveDTO();

        dto.setTransactionId("AAAAAA34FGDDD45");
        dto.setDescription("APPROVED");

        return dto;
    }
}
