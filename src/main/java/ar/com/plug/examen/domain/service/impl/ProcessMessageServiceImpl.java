package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Message;
import ar.com.plug.examen.domain.service.ProcessMessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProcessMessageServiceImpl implements ProcessMessageService {

    @Override
    public Message processMessage(String message) {
        return new Message(message);
    }
}
