package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.ClienteDto;
import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.service.ClienteService;
import ar.com.plug.examen.exception.MicroserviceErrorException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@RestController
@Api(value = "ClienteController", tags = "Operaciones pertenecientes a cliente.")
@RequestMapping(path = "/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ModelMapper modelMapper;

    @ApiOperation(
            value = "Ver una lista de clientes disponibles",
            response = ClienteDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista recuperada con éxito"),
            @ApiResponse(code = 401, message = "No tienes autorización para ver el recurso."),
            @ApiResponse(code = 403, message = "Está prohibido acceder al recurso al que intentaba acceder"),
            @ApiResponse(code = 404, message = "No se encuentra el recurso al que intentaba acceder ")
    })
    @GetMapping("/")
    public ResponseEntity<List<ClienteDto>> index() {
        try {
            log.info("Listando clientes");
            List<ClienteDto> clients = clienteService.findAll()
                    .stream()
                    .map(cliente -> modelMapper.map(cliente, ClienteDto.class))
                    .collect(Collectors.toList());
            if (clients.isEmpty()) {
                log.warn("No hay clientes registrados");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Agregar un cliente")
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody @Valid ClienteDto clienteDto, BindingResult result) {

        log.info("Creando cliente");
        Map<String, Object> response = validErrors(result);

        if (Objects.nonNull(response)) {
            log.error("Los datos no se ingresaron correctamente");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Cliente clienteRequest = modelMapper.map(clienteDto, Cliente.class);
            Cliente c = clienteService.save(clienteRequest);
            ClienteDto clienteResponse = modelMapper.map(c, ClienteDto.class);
            return new ResponseEntity<>(clienteResponse, HttpStatus.CREATED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Buscar un cliente por un ID", response = ClienteDto.class)
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> show(@PathVariable Integer id) {
        log.info("Visualización de un cliente.");
        Cliente cliente = clienteService.findById(id);
        if (Objects.nonNull(cliente)) {
            ClienteDto clienteResponse = modelMapper.map(cliente, ClienteDto.class);
            return new ResponseEntity<>(clienteResponse, HttpStatus.OK);
        } else {
            log.warn("El cliente no existe.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Actualizar un cliente")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @RequestBody @Valid ClienteDto clienteDto,
            BindingResult result,
            @PathVariable Integer id) throws MicroserviceErrorException {

        // Variables
        Map<String, Object> response = validErrors(result);

        if (Objects.nonNull(response)) {
            log.error("se ingresaron datos errados.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        log.info("Actualización de datos de cliente: " + id);
        Cliente clienteRequest = modelMapper.map(clienteDto, Cliente.class);
        Cliente cliente = clienteService.update(clienteRequest, id);
        if (Objects.nonNull(cliente)) {
            ClienteDto clienteResponse = modelMapper.map(cliente, ClienteDto.class);
            return new ResponseEntity<>(clienteResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Eliminar un cliente")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            log.info("Eliminando cliente");
            clienteService.delete(id);
            response.put("mensaje", "El cliente ha sido eliminado con éxito");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            response.put("mensaje", "Error al eliminar de la base de datos");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> validErrors(BindingResult result) {
        Map<String, Object> response = null;
        if (result.hasErrors()) {
            response = new HashMap<>();
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return response;
        }
        return response;
    }
}
