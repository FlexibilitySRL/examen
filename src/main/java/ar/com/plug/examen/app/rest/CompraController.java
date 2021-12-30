package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.ClienteDto;
import ar.com.plug.examen.domain.dto.CompraDto;
import ar.com.plug.examen.domain.dto.CompraSaveDto;
import ar.com.plug.examen.domain.model.Compra;
import ar.com.plug.examen.domain.service.CompraService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@RestController
@Api(value = "CompraController", tags = "Operaciones pertenecientes a compra.")
@RequestMapping(path = "/compras")
public class CompraController {

    @Autowired
    CompraService compraService;

    @Autowired
    ModelMapper modelMapper;

    @ApiOperation(
            value = "Ver una lista de compras disponibles",
            response = ClienteDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista recuperada con éxito"),
            @ApiResponse(code = 401, message = "No tienes autorización para ver el recurso."),
            @ApiResponse(code = 403, message = "Está prohibido acceder al recurso al que intentaba acceder"),
            @ApiResponse(code = 404, message = "No se encuentra el recurso al que intentaba acceder ")
    })
    @GetMapping("/")
    public ResponseEntity<List<CompraDto>> index() {
        try {
            log.info("Listando de compras");
            List<CompraDto> compras = compraService.findAll()
                    .stream()
                    .map(compra -> modelMapper.map(compra, CompraDto.class))
                    .collect(Collectors.toList());
            if (compras.isEmpty()) {
                log.warn("No hay compras registradas");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(compras, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Buscar una compra por un ID", response = CompraDto.class)
    @GetMapping("/{id}")
    public ResponseEntity<CompraDto> show(@PathVariable Integer id) {
        log.info("Visualizacion de la compra con el ID: " + id);
        Compra compra = compraService.findCompraById(id);
        if (Objects.nonNull(compra)) {
            Double total = compra.getTotal();
            CompraDto compraResponse = modelMapper.map(compra, CompraDto.class);
            compraResponse.setTotal(total);
            return new ResponseEntity<>(compraResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Eliminar una compra")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        try {
            log.info("Eliminando compra");
            compraService.deleteCompraById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Agregar una compra")
    @PostMapping("/")
    public ResponseEntity<CompraSaveDto> crear(@RequestBody CompraSaveDto compraDto) {
        try {
            log.info("Creando compra");
            Compra compraRequest = modelMapper.map(compraDto, Compra.class);
            Compra c = compraService.saveCompra(compraRequest);
            CompraSaveDto compraResponse = modelMapper.map(c, CompraSaveDto.class);
            return new ResponseEntity<>(compraResponse, HttpStatus.CREATED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Actualizar una compra")
    @PutMapping("/{id}")
    public ResponseEntity<CompraSaveDto> updateCompra(
            @PathVariable("id") Integer id,
            @RequestBody CompraSaveDto compraDto) throws MicroserviceErrorException {

        log.info("Actualizando la compra con el ID: " + id);
        Compra compraRequest = modelMapper.map(compraDto, Compra.class);
        Compra compra = compraService.updateCompra(compraRequest, id);

        if (Objects.nonNull(compra)) {
            CompraSaveDto compraResponse = modelMapper.map(compra, CompraSaveDto.class);
            return new ResponseEntity<>(compraResponse, HttpStatus.OK);
        } else {
            log.warn("No se encontro la compra");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Aprobar una compra")
    @PutMapping("/aprobar/{id}")
    public ResponseEntity<CompraSaveDto> aprobarCompra(
            @PathVariable("id") Integer id) throws MicroserviceErrorException {

        log.info("Aprobar la compra con el ID: " + id);
        Compra compra = compraService.aprobarCompra(id);

        if (Objects.nonNull(compra)) {
            CompraSaveDto compraResponse = modelMapper.map(compra, CompraSaveDto.class);
            return new ResponseEntity<>(compraResponse, HttpStatus.OK);
        } else {
            log.warn("No se encontro la compra");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}