package ar.com.plug.examen.infrastructure.rest.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.Product;
import ar.com.plug.examen.domain.service.ServiceDomain;
import ar.com.plug.examen.infrastructure.rest.dto.ProductRequestDto;
import ar.com.plug.examen.infrastructure.rest.dto.ProductResponseDto;
import ar.com.plug.examen.infrastructure.rest.dto.ResponseDto;
import ar.com.plug.examen.shared.config.MenssageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Products", description = "Catálogo de productos")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(path = ProductController.PATH)
public class ProductController {
    private final ServiceDomain<Product> productService;
    private final MenssageResponse menssageResponse;
    public final static String PATH = "/product";
    public final static String PRODUCT_BY_FILTER = "all";

    @Operation(summary = "Obtiene un producto por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente por id", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontro el producto", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<ProductResponseDto> findById(@RequestParam Integer id) {
        return new ResponseEntity<>(new ProductResponseDto(productService.findById(id)), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una lista de productos por filtros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos", content = @Content(schema = @Schema(implementation = ProductResponseDto.class)))
    })
    @GetMapping(path = PRODUCT_BY_FILTER)
    public ResponseEntity<List<ProductResponseDto>> findAllByFilter(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String description) {
        return new ResponseEntity<>(productService.findAllByFilter(Product.builder()
                .name(name)
                .description(description).build()).stream().map(ProductResponseDto::new).collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @Operation(summary = "Crea un producto por medio de un json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Crea el producto y lo devuelve", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductResponseDto.class)))),
            @ApiResponse(responseCode = "409", description = "Ya el producto existe", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Errores en los campos del request", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody @Valid ProductRequestDto productRequestDto) {
        return new ResponseEntity<>(new ProductResponseDto(productService.create(productRequestDto.toProduct())),
                HttpStatus.OK);
    }

    @Operation(summary = "Actualiza un producto por medio de un json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualiza el producto y lo devuelve", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontro el producto", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "409", description = "Ya el producto existe", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Errores en los campos del request", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @PatchMapping
    public ResponseEntity<ProductResponseDto> upDate(@RequestBody @Valid ProductRequestDto productRequestDto) {
        return new ResponseEntity<>(new ProductResponseDto(productService.upDate(productRequestDto.toProduct())),
                HttpStatus.OK);
    }

    @Operation(summary = "Elimina un producto por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "producto el cliente ", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No se encontro el producto", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Errores en el request", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @DeleteMapping
    public ResponseEntity<ResponseDto> remove(@RequestParam Integer id) {
        productService.remove(id);
        return new ResponseEntity<>(ResponseDto.builder()
                .code(MenssageResponse.OK)
                .message(menssageResponse.getMessages().get(MenssageResponse.OK))
                .build(), HttpStatus.OK);
    }

}
