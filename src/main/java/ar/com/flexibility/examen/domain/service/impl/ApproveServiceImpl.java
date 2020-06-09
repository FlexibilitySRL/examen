package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.dto.ApproveDTO;
import ar.com.flexibility.examen.domain.exception.PurchaseException;
import ar.com.flexibility.examen.domain.model.Approve;
import ar.com.flexibility.examen.domain.repository.ApproveRepository;
import ar.com.flexibility.examen.domain.service.ApproveService;
import ar.com.flexibility.examen.domain.service.TransactionPurchasesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApproveServiceImpl implements ApproveService {

    @Autowired
    private ApproveRepository approveRepository;

    @Autowired
    TransactionPurchasesService transactionPurchasesService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ApproveDTO approve(String transactionId) {
        if(!transactionPurchasesService.existTransactionId(transactionId)){
                throw new PurchaseException("No faund transacionId: " + transactionId);
        }

        if(approveRepository.findByTransactionId(transactionId).isPresent()){
            throw new PurchaseException("Transaction has already been approved: " + transactionId );
        }

        Approve approve =  approveRepository.save(new Approve(transactionId, APPROVED));

        return toApproveDto(approve);
    }

    public ApproveDTO toApproveDto(Approve approve){
        ApproveDTO approveDto = modelMapper.map(approve, ApproveDTO.class);
        return approveDto;
    }
}
