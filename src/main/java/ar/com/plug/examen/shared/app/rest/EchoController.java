package ar.com.plug.examen.shared.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.shared.app.api.MessageApi;
import ar.com.plug.examen.shared.domain.service.ProcessMessageService;

@RestController
@RequestMapping(path = "/echo")
public class EchoController {

    private final ProcessMessageService messageService;

    @Autowired
    public EchoController (ProcessMessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE },
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> echo(@RequestBody MessageApi message)
    {
        return new ResponseEntity<>(messageService.processMessage(message.getMessage()), HttpStatus.OK);
    }
}
