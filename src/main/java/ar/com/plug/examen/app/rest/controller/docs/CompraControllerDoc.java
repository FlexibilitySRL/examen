package ar.com.plug.examen.app.rest.controller.docs;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.http.ResponseEntity;

import ar.com.plug.examen.domain.model.dto.ClienteCompraRestDto;
import ar.com.plug.examen.domain.model.dto.CompraRestDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface CompraControllerDoc {

	

	@ApiOperation(
        value = "Retorna la lista de todos las compras",
        notes = "Retorna vacio si no hay compras",
		tags = {"Compras"}
		)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK!!!"),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Ups, algo puede malir sal")}
		)
    ResponseEntity<List<CompraRestDto>> getCompras();
	
	
	
	@ApiOperation(
	        value = "Retorna una compra especifica",
	        notes = "Retorna vacio si no hay compra",
			tags = {"Compras"}
			)
		@ApiResponses(value = {
				@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK!!!"),
				@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Ups, algo puede malir sal")}
			)
    ResponseEntity<CompraRestDto> getCompraPorId(final Long id);

	
	@ApiOperation(
	        value = "Crea o actualiza una compra",
	        notes = "Crea o actualiza una compra",
			tags = {"Compras"}
			)
		@ApiResponses(value = {
				@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK!!!"),
				@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Ups, algo puede malir sal")}
			)
	ResponseEntity<CompraRestDto> update(final CompraRestDto compraRestDto);


	@ApiOperation(
	        value = "Elimina una compra por su Id",
	        notes = "Elimina una compra por su Id",
			tags = {"Compras"}
			)
		@ApiResponses(value = {
				@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK!!!"),
				@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Ups, algo puede malir sal")}
			)
	ResponseEntity<Boolean> removeCompra(final Long id);

	@ApiOperation(
	        value = "Trae todas las compras de un cliente",
	        notes = "Trae todas las compras de un cliente",
			tags = {"Compras"}
			)
		@ApiResponses(value = {
				@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK!!!"),
				@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Ups, algo puede malir sal")}
			)
	ResponseEntity<ClienteCompraRestDto> getComprasByCliente(final Long id);

	
	
	@ApiOperation(
	        value = "Aprueba una compra",
	        notes = "Aprueba una compra",
			tags = {"Compras"}
			)
		@ApiResponses(value = {
				@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK!!!"),
				@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Ups, algo puede malir sal")}
			)
	ResponseEntity<Boolean> aprobarCompra(final Long id);

}
