package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.entity.Message;
import ar.com.plug.examen.domain.service.ProcessMessageService;
import org.springframework.stereotype.Service;

@Service
public class ProcessMessageServiceImpl implements ProcessMessageService {

    @Override
    public Message processMessage(String message) {
        return new Message(message);
    }
}
