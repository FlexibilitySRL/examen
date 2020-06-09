package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.dto.ApproveDTO;

public interface ApproveService {
    public static final String APPROVED = "APPROVED";

    ApproveDTO approve(String transactionId);
}
