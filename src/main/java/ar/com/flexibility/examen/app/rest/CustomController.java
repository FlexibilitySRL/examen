package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.domain.model.Message;
import ar.com.flexibility.examen.domain.service.ProcessMessageService;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomController {

    @Autowired
    private ProcessMessageService messageService;

    public Message buildResponse(String message)
    {
        return messageService.processMessage(message);
    }
}
