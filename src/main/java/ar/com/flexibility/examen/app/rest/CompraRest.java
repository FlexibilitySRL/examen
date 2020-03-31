package ar.com.flexibility.examen.app.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexibility.examen.domain.model.Compra;
import ar.com.flexibility.examen.domain.model.Compra.Estado;
import ar.com.flexibility.examen.domain.service.CompraService;
import ar.com.genomasoft.jproject.webutils.webservices.BaseAuditedEntityWebService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("Cliente - Servicio web REST")
@RequestMapping(path = "/compras")
public class CompraRest extends BaseAuditedEntityWebService<Compra, CompraService> {
	
	@Autowired
	CompraService comprasrv;

	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method=RequestMethod.POST, value =  "/aprobarCompra/{idCompra}")
	@ApiOperation(value = "aprobar compra")
	@ResponseBody
	public void  aprobar(
				@PathVariable Integer idCompra,
				HttpServletRequest request, 
				HttpServletResponse response) throws Exception {
		
		Compra com= comprasrv.loadEntityById(idCompra);
		
		com.setEstado(Estado.APROBADO);
		
		comprasrv.equals(com);
		
			 
	}

}
