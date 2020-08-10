package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.MessageResponse;
import ar.com.flexibility.examen.domain.service.ProcessMessageService;
import org.springframework.stereotype.Service;

@Service
public class ProcessMessageServiceImpl implements ProcessMessageService {

    @Override
    public MessageResponse processMessage(String message) {
        return new MessageResponse(message);
    }
}
