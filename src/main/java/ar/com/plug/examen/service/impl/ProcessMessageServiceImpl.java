package ar.com.plug.examen.service.impl;

import ar.com.plug.examen.domain.model.Message;
import ar.com.plug.examen.service.ProcessMessageService;
import org.springframework.stereotype.Service;

@Service
public class ProcessMessageServiceImpl implements ProcessMessageService {

    @Override
    public Message processMessage(String message) {
        return new Message(message);
    }
}
