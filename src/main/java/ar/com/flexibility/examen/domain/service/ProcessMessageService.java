package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.MessageResponse;


public interface ProcessMessageService {

    MessageResponse processMessage(String string);
}
