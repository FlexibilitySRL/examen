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

	/**
	 * Metodo que permite guardar la transacción
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/v1/transaccion", produces = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<TransaccionBean> crearTransaccion(@RequestBody TransaccionBean bean) {
		try {
			bean = service.crearTransaccion(bean);
			logger.info("Se guarda correctamente la transaccion con id " + bean.getIdCliente());
			return new ResponseEntity<TransaccionBean>(bean, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error en la creación de la transaccion " + e.getMessage());
			return new ResponseEntity<TransaccionBean>(bean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Metodo que permite consultar transacciones por el código
	 * @param codigoTrx
	 * @return
	 */
	@RequestMapping(value = "/v1/transaccion/{codigoTrx}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<TransaccionBean> consultaTransaccion(@PathVariable String codigoTrx) {
		logger.info("Consultando transacción por id compra: {}", codigoTrx);
		return new ResponseEntity<TransaccionBean>(service.getTransaccionById(codigoTrx), HttpStatus.OK);
	}

	/**
	 * Metodo que permite actualizar el estado de la compra a APROBADO
	 * @param codigoTrx
	 * @param bean2
	 * @return
	 */
	@RequestMapping(value = "/v1/transaccion/{codigoTrx}", method = RequestMethod.PUT)
	public ResponseEntity<TransaccionBean> actualizarEstadoCompra(@PathVariable String codigoTrx,
			@RequestBody TransaccionBean bean2) {
		TransaccionBean bean = new TransaccionBean();
		try {
			bean = service.getTransaccionById(codigoTrx);
			if (bean == null) {
				return new ResponseEntity<TransaccionBean>(HttpStatus.NOT_FOUND);
			}
			bean.setEstado(bean2.getEstado());
			bean = service.crearTransaccion(bean);
			logger.info("Se actualiza correctamente el cliente con id " + bean.getIdCliente());
			return new ResponseEntity<TransaccionBean>(bean, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error en la actualización del transacción " + e.getMessage());
			return new ResponseEntity<TransaccionBean>(bean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
