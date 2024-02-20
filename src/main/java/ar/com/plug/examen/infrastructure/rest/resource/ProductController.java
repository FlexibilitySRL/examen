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
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(path = ProductController.PATH)
public class ProductController {
    private final ServiceDomain<Product> productService;
    private final MenssageResponse menssageResponse;
    public final static String PATH = "/product";
    public final static String PRODUCT_BY_FILTER = "all";

    @GetMapping
    public ResponseEntity<ProductResponseDto> findById(@RequestParam String id) {
        return new ResponseEntity<>(new ProductResponseDto(productService.findById(id)), HttpStatus.OK);
    }

    @GetMapping(path = PRODUCT_BY_FILTER)
    public ResponseEntity<List<ProductResponseDto>> findAllByFilter(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String description) {
        return new ResponseEntity<>(productService.findAllByFilter(Product.builder()
                .name(name)
                .description(description).build()).stream().map(ProductResponseDto::new).collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody @Valid ProductRequestDto productRequestDto) {
        return new ResponseEntity<>(new ProductResponseDto(productService.create(productRequestDto.toProduct())),
                HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<ProductResponseDto> upDate(@RequestBody @Valid ProductRequestDto productRequestDto) {
        return new ResponseEntity<>(new ProductResponseDto(productService.upDate(productRequestDto.toProduct())),
                HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> remove(@RequestParam String id) {
        productService.remove(id);
        return new ResponseEntity<>(ResponseDto.builder()
                .code(MenssageResponse.OK)
                .message(menssageResponse.getMessages().get(MenssageResponse.OK))
                .build(), HttpStatus.OK);
    }

}
