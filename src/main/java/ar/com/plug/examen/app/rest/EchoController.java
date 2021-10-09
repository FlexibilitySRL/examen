package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.MessageApi;
import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.domain.model.Transaccion;
import ar.com.plug.examen.domain.service.ProcessMessageService;
import ar.com.plug.examen.domain.service.impl.ProcessClienteServiceImpl;
import ar.com.plug.examen.domain.service.impl.ProcessProductoServiceImpl;
import ar.com.plug.examen.domain.service.impl.ProcessTransaccionServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/echo")
public class EchoController {

	private final ProcessMessageService messageService;
	@Autowired
	ProcessClienteServiceImpl serviceCliente;
	@Autowired
	ProcessProductoServiceImpl serviceProducto;
	@Autowired
	ProcessTransaccionServiceImpl serviceTransaccion;

	@Autowired
	public EchoController(ProcessMessageService messageService) {
		this.messageService = messageService;
	}

	@PostMapping(path = "", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> echo(@RequestBody MessageApi message) {
		return new ResponseEntity<>(messageService.processMessage(message.getMessage()), HttpStatus.OK);
	}

//	<editor-fold defaultstate="collapsed" desc="guardarCliente metodo para agregar cliente por medio de JSON body">
	@PostMapping(path = "/cliente", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Cliente guardarCliente(@RequestBody Cliente cliente) {
		return this.serviceCliente.addCliente(cliente);
	}
//	</editor-fold>

//	<editor-fold defaultstate="collapsed" desc="traerCliente metodo para traer Cliente por medio de id">
	@GetMapping(path = "/cliente")
	public Optional<Cliente> traerCliente(@RequestParam(value = "id", defaultValue = "0") Integer id) {
		return this.serviceCliente.getCliente(id);
	}
//	</editor-fold>

//	<editor-fold defaultstate="collapsed" desc="deleteCliente metodo para eliminar Cliente por medio de id">
	@DeleteMapping(path = "/cliente")
	public void deleteCliente(@RequestParam(value = "id", defaultValue = "0") Integer id) {
		this.serviceCliente.delete(id);
	}
//	</editor-fold>

//	<editor-fold defaultstate="collapsed" desc="updateCliente metodo para actualizar cliente por medio de JSON Body">
	@PutMapping(path = "/cliente", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Cliente updateCliente(@RequestBody Cliente cliente) {
		return this.serviceCliente.addCliente(cliente);
	}
//	</editor-fold>

//ProductoControll

//	<editor-fold defaultstate="collapsed" desc="guardarProducto metodo para actualizar Producto por medio de JSON Body">
	@PostMapping(path = "/producto", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Producto guardarProducto(@RequestBody Producto producto) {
		return this.serviceProducto.addProducto(producto);
	}
//	</editor-fold>

//	<editor-fold defaultstate="collapsed" desc="traerProducto metodo para traer Producto por medio de id">
	@GetMapping(path = "/producto")
	public Optional<Producto> traerProducto(@RequestParam(value = "id", defaultValue = "0") Integer id) {
		return this.serviceProducto.getProducto(id);
	}
//	</editor-fold>

//	<editor-fold defaultstate="collapsed" desc="deleteProducto metodo para eliminar Producto por medio de id">
	@DeleteMapping(path = "/producto")
	public void deleteProducto(@RequestParam(value = "id", defaultValue = "0") Integer id) {
		this.serviceProducto.delete(id);
	}
//	</editor-fold>

//	<editor-fold defaultstate="collapsed" desc="updateProducto metodo para actualizar Producto por medio de JSON Body">
	@PutMapping(path = "/producto", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Producto updateProducto(@RequestBody Producto producto) {
		return this.serviceProducto.addProducto(producto);
	}
//	</editor-fold>

//TransaccionControll

//	<editor-fold defaultstate="collapsed" desc="guardarTransaccion metodo para actualizar Transaccion por medio de JSON Body">
	@PostMapping(path = "/transaccion", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Transaccion guardarTransaccion(@RequestBody Transaccion transaccion) {
		return this.serviceTransaccion.addTransaccion(transaccion);
	}

//	<editor-fold defaultstate="collapsed" desc="traerTransaccion metodo para traer transaccion por medio de id">
	@GetMapping(path = "/transaccion")
	public Optional<Transaccion> traerTransaccion(@RequestParam(value = "id", defaultValue = "0") Integer id) {
		return this.serviceTransaccion.getTransaccion(id);
	}
//	</editor-fold>
}
