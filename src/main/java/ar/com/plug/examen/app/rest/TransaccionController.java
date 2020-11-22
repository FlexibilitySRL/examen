package ar.com.plug.examen.app.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.app.api.TransaccionBean;
import ar.com.plug.examen.domain.service.impl.TransaccionServiceImpl;

@RestController
public class TransaccionController {
	
	private static final Logger logger = LoggerFactory.getLogger(TransaccionController.class);
	
	@Autowired
	private TransaccionServiceImpl service;
	
	@RequestMapping(value = "/v1/transaccion", produces = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<TransaccionBean> crearTransaccion(@RequestBody TransaccionBean bean) {
		try {
			bean = service.createCliente(bean);
			logger.info("Se guarda correctamente la transaccion con id "+bean.getIdCliente());
			return new ResponseEntity<TransaccionBean>(bean, HttpStatus.OK);
		} catch (Exception e) {
			logger.error( "Error en la creación de la transaccion " + e.getMessage());
			return new ResponseEntity<TransaccionBean>(bean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/v1/transaccion/{codigoTrx}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<TransaccionBean> consultaTransaccion(@PathVariable Long codigoTrx) {
		logger.info("Consultando transacción por id compra: {}", codigoTrx);
		return new ResponseEntity<TransaccionBean>(service.getProductoByID(codigoTrx), HttpStatus.OK);
		return null;
	}
//	
//	@RequestMapping(value = "/v1/transaccion/{id}", method = RequestMethod.PUT)
//	public ResponseEntity<TransaccionBean> actualizarEstado(@PathVariable Long id, @RequestBody TransaccionBean bean) {
//		try {
//		} catch (Exception e) {
//		}
//	}

}
