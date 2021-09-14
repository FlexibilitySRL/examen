package ar.com.plug.examen.app.rest.controller.docs;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.http.ResponseEntity;

import ar.com.plug.examen.domain.model.dto.ProductoRestDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface ProductoControllerDoc {

	

	@ApiOperation(
        value = "Retorna la lista de todos los productos",
        notes = "Retorna vacio si no hay productos",
		tags = {"Productos"}
		)
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK!!!"),
			@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Ups, algo puede malir sal")}
		)
    ResponseEntity<List<ProductoRestDto>> getProductos();
	
	
	@ApiOperation(
	        value = "Retorna un producto especifico",
	        notes = "Retorna vacio si no hay producto",
			tags = {"Productos"}
			)
		@ApiResponses(value = {
				@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK!!!"),
				@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Ups, algo puede malir sal")}
			)
    ResponseEntity<ProductoRestDto> getProductoPorId(final Long id);

	
	@ApiOperation(
	        value = "Crea o actualiza un producto",
	        notes = "Crea o actualiza un producto",
			tags = {"Productos"}
			)
		@ApiResponses(value = {
				@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK!!!"),
				@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Ups, algo puede malir sal")}
			)
	ResponseEntity<ProductoRestDto> update(final ProductoRestDto productoRestDto);


	@ApiOperation(
	        value = "Elimina un producto por su Id",
	        notes = "Elimina un producto por su Id",
			tags = {"Productos"}
			)
		@ApiResponses(value = {
				@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK!!!"),
				@ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Ups, algo puede malir sal")}
			)
	ResponseEntity<Boolean> removeProducto(final Long id);

	
	
}
