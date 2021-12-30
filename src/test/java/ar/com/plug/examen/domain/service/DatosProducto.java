package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.ProductoDto;
import ar.com.plug.examen.domain.model.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DatosProducto {

    public final static Producto createProducto() {
        return Producto
                .builder()
                .nombre("Televisor samsung")
                .precio(1200.00)
                .build();
    }

    public final static ProductoDto createProductoDto() {
        return ProductoDto
                .builder()
                .id(1)
                .nombre("Televisor samsung")
                .precio(1200.00)
                .build();
    }

    public final static Optional<Producto> createProductoOptional() {
        return Optional.of(createProducto());
    }

    public final static Producto updateProducto() {
        return Producto
                .builder()
                .id(1)
                .nombre("Televisor samsung")
                .precio(1200.00)
                .build();
    }

    public final static Optional<Producto> updateProductoOptional() {
        return Optional.of(updateProducto());
    }

    public final static List<Producto> datosProductos() {
        return Arrays.asList(
                Producto.builder()
                        .id(1)
                        .nombre("Televisor LG 55")
                        .precio(1500.00)
                        .build(),
                Producto.builder()
                        .id(2)
                        .nombre("Televisor Samsung 55")
                        .precio(1600.00)
                        .build());
    }

    public final static List<ProductoDto> datosProductoDto() {
        return Arrays.asList(
                ProductoDto.builder()
                        .id(1)
                        .nombre("Televisor LG 55")
                        .precio(1500.00)
                        .build(),
                ProductoDto.builder()
                        .id(2)
                        .nombre("Televisor Samsung 55")
                        .precio(1600.00)
                        .build());
    }
}