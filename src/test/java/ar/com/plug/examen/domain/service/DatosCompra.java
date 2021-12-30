package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.ClienteDto;
import ar.com.plug.examen.domain.dto.CompraDto;
import ar.com.plug.examen.domain.dto.ItemCompraDto;
import ar.com.plug.examen.domain.dto.ProductoDto;
import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.model.Compra;
import ar.com.plug.examen.domain.model.ItemCompra;
import ar.com.plug.examen.domain.model.Producto;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class DatosCompra {

    public final static Compra createCompra() {
        return Compra
                .builder()
                .descripcion("Compra prueba")
                .observacion("Compra prueba")
                .createAt(new Date())
                .cliente(Cliente.builder().id(1).nombre("Carlos").apellido("Borre").build())
                .items(Arrays.asList(
                        ItemCompra
                                .builder()
                                .cantidad(3)
                                .producto(
                                        Producto
                                                .builder()
                                                .id(1)
                                                .nombre("Zapatos")
                                                .precio(200.00)
                                                .build())
                                .build()))
                .build();
    }

    public final static CompraDto createCompraDto() {
        return CompraDto
                .builder()
                .descripcion("Compra prueba")
                .observacion("Compra prueba")
                .createAt(new Date())
                .cliente(ClienteDto.builder().id(1).nombre("Carlos").apellido("Borre").build())
                .items(Arrays.asList(
                        ItemCompraDto
                                .builder()
                                .cantidad(3)
                                .producto(
                                        ProductoDto
                                                .builder()
                                                .id(1)
                                                .nombre("Zapatos")
                                                .precio(200.00)
                                                .build())
                                .build()))
                .build();
    }

    public final static Optional<Compra> createCompraOptional() {
        return Optional.of(createCompra());
    }

    public final static Compra updateCompra() {
        return Compra
                .builder()
                .id(1)
                .descripcion("Compra prueba")
                .observacion("Compra prueba")
                .createAt(new Date())
                .cliente(Cliente.builder().id(1).nombre("Carlos").apellido("Borre").build())
                .items(Arrays.asList(
                        ItemCompra
                                .builder()
                                .cantidad(3)
                                .producto(
                                        Producto
                                                .builder()
                                                .id(1)
                                                .nombre("Zapatos")
                                                .precio(200.00)
                                                .build())
                                .build()))
                .build();
    }

    public final static Optional<Compra> updateCompraOptional() {
        return Optional.of(updateCompra());
    }

    public final static List<Compra> datosCompras() {
        return Arrays.asList(
                Compra
                        .builder()
                        .descripcion("Compra prueba")
                        .observacion("Compra prueba")
                        .createAt(new Date())
                        .cliente(Cliente.builder().id(1).nombre("Carlos").apellido("Borre").build())
                        .items(Arrays.asList(
                                ItemCompra
                                        .builder()
                                        .cantidad(3)
                                        .producto(
                                                Producto
                                                        .builder()
                                                        .id(1)
                                                        .nombre("Zapatos")
                                                        .precio(200.00)
                                                        .build())
                                        .build()))
                        .build());
    }

    public final static List<CompraDto> datosCompraDto() {
        return Arrays.asList(
                CompraDto
                        .builder()
                        .descripcion("Compra prueba")
                        .observacion("Compra prueba")
                        .createAt(new Date())
                        .cliente(ClienteDto.builder().id(1).nombre("Carlos").apellido("Borre").build())
                        .items(Arrays.asList(
                                ItemCompraDto
                                        .builder()
                                        .cantidad(3)
                                        .producto(
                                                ProductoDto
                                                        .builder()
                                                        .id(1)
                                                        .nombre("Zapatos")
                                                        .precio(200.00)
                                                        .build())
                                        .build()))
                        .build());
    }
}