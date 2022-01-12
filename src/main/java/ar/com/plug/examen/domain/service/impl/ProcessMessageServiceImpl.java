package ar.com.plug.examen.domain.service.impl;

import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.Message;
import ar.com.plug.examen.domain.service.ProcessMessageService;

@Service
public class ProcessMessageServiceImpl implements ProcessMessageService {

    @Override
    public Message processMessage(String message) {
        return new Message(message);
    }
}
