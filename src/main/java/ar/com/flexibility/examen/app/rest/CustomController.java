package ar.com.flexibility.examen.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.app.api.MessageApi;
import ar.com.flexibility.examen.domain.model.Message;
import ar.com.flexibility.examen.domain.service.ProcessMessageService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/custom")
public class CustomController {

    @Autowired
    private ProcessMessageService messageService;

    @ApiOperation(value = "Impresión de texto", response = Message.class)
    @PostMapping("/echo")
    public ResponseEntity<?> echo(@RequestBody MessageApi message)
    {
        return new ResponseEntity<>(messageService.processMessage(message.getMessage()), HttpStatus.OK);
    }
}
