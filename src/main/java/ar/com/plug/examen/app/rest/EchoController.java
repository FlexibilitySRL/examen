package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.MessageApi;
import ar.com.plug.examen.domain.service.ProcessMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "ECHO Service")
@RestController
@RequestMapping(path = "/echo")
public class EchoController {

    private final ProcessMessageService messageService;

    @Autowired
    public EchoController (ProcessMessageService messageService) {
        this.messageService = messageService;
    }

    @ApiOperation(value = "Returns an echo of input message")
    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> echo(@RequestBody MessageApi message) {
        return new ResponseEntity<>(messageService.processMessage(message.getMessage()), HttpStatus.OK);
    }
}
