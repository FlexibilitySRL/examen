package ar.com.plug.examen.app.rest.services.impl;

import ar.com.plug.examen.app.rest.model.Message;
import ar.com.plug.examen.app.rest.services.ProcessMessageService;
import org.springframework.stereotype.Service;

@Service
public class ProcessMessageServiceImpl implements ProcessMessageService {

    @Override
    public Message processMessage(String message) {
        return new Message(message);
    }
}
