package ar.com.flexibility.examen.app.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.flexibility.examen.domain.service.ClienteService;
import ar.com.genomasoft.jproject.webutils.webservices.BaseAuditedEntityWebService;
import io.swagger.annotations.Api;
import ar.com.genomasoft.jproject.webutils.webservices.BaseAuditedEntityWebService;

@RestController
@Api("Cliente - Servicio web REST")
@RequestMapping(path = "/clientes")
public class ClienteRest extends BaseAuditedEntityWebService<Cliente, ClienteService> {
	
}
