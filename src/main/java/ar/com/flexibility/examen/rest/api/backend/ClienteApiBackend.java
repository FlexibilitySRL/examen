package ar.com.flexibility.examen.rest.api.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import ar.com.flexibility.examen.domain.service.ClientService;
import ar.com.flexibility.examen.factory.ClientFactory;
import ar.com.flexibility.examen.factory.ClienteDTOFactory;
import ar.com.flexibility.examen.rest.api.model.ClienteDTO;
import ar.com.flexibility.examen.rest.api.model.RespuestaDTO;

@Component
public class ClienteApiBackend {

	private final Logger logger = LoggerFactory.getLogger(ClienteApiBackend.class);

	@Autowired
	private ClientService clientService;

	/**
	 * Metodo que permite la creacion de un cliente
	 * 
	 * @param clienteDTO
	 * @return
	 */
	public ResponseEntity<ClienteDTO> crearCliente(ClienteDTO clienteDTO) {
		logger.debug("Inicia proceso de creacion de un cliente, con los siguientes datos {}", clienteDTO);
		ClienteDTO clienteDTOResponse = ClienteDTOFactory.INSTANCE
				.from(clientService.createClient(ClientFactory.INSTANCE.from(clienteDTO)));
		logger.debug("Finaliza proceso de creacion del cliente {}", clienteDTOResponse);
		return new ResponseEntity<>(clienteDTOResponse, HttpStatus.OK);
	}

	/**
	 * Metodo que permite la actualizacion del cliente
	 * 
	 * @param id
	 * @param clienteDTO
	 * @return
	 */
	public ResponseEntity<ClienteDTO> actualizarCliente(String id, ClienteDTO clienteDTO) {
		logger.debug("Inicia proceso de actualizacion de un cliente, con los siguientes datos {}", clienteDTO);
		ClienteDTO clienteDTOResponse = ClienteDTOFactory.INSTANCE
				.from(clientService.updateClient(ClientFactory.INSTANCE.from(id, clienteDTO)));
		logger.debug("Finaliza proceso de actualizacion de un cliente, con los siguientes datos {}", clienteDTO);
		return new ResponseEntity<>(clienteDTOResponse, HttpStatus.OK);
	}

	/**
	 * Metodo que permite eliminar la informacion de un cliente
	 * 
	 * @param id
	 * @return
	 */
	public ResponseEntity<RespuestaDTO> eliminarCliente(String id) {
		logger.debug("Inicia proceso de eliminar de un cliente con el id {}", id);
		clientService.deleteClient(id);
		RespuestaDTO respuestaDTO = new RespuestaDTO();
		respuestaDTO.setCode(HttpStatus.OK.value());
		respuestaDTO.setMessage("Registro eliminado correctamente");
		logger.debug("Finaliza proceso de eliminar de un cliente con el id {}", id);
		return new ResponseEntity<>(respuestaDTO, HttpStatus.OK);
	}

}
