package ar.com.flexibility.examen.app.service.impl;

import ar.com.flexibility.examen.domain.model.Message;
import ar.com.flexibility.examen.app.service.ProcessMessageService;
import org.springframework.stereotype.Service;

@Service
public class ProcessMessageServiceImpl implements ProcessMessageService {

    @Override
    public Message processMessage(String message) {
        return new Message(message);
    }
}
