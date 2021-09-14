package ar.com.plug.examen.app.rest.controller.docs;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.http.ResponseEntity;

import ar.com.plug.examen.domain.model.dto.ClienteRestDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface ClienteControllerDoc {

	

	@ApiOperation(
        value = "Retorna la lista de todos los clientes",
        notes = "Retorna vacio si no hay clientes",
		tags = {"Clientes"}
		)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK!!!"),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Ups, algo puede malir sal")}
		)
    ResponseEntity<List<ClienteRestDto>> getClientes();
	
	
	@ApiOperation(
	        value = "Retorna un cliente especifico",
	        notes = "Retorna vacio si no hay cliente",
			tags = {"Clientes"}
			)
		@ApiResponses(value = {
				@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK!!!"),
				@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Ups, algo puede malir sal")}
			)
    ResponseEntity<ClienteRestDto> getClientePorId(final Long id);

	
	@ApiOperation(
	        value = "Crea o actualiza un cliente",
	        notes = "Crea o actualiza un cliente",
			tags = {"Clientes"}
			)
		@ApiResponses(value = {
				@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK!!!"),
				@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Ups, algo puede malir sal")}
			)
	ResponseEntity<ClienteRestDto> update(final ClienteRestDto clienteRestDto);


	@ApiOperation(
	        value = "Elimina un cliente por su Id",
	        notes = "Elimina un cliente por su Id",
			tags = {"Clientes"}
			)
		@ApiResponses(value = {
				@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK!!!"),
				@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Ups, algo puede malir sal")}
			)
	ResponseEntity<Boolean> removeCliente(final Long id);

	
	
}
