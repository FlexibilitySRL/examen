package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.ClienteDto;
import ar.com.plug.examen.domain.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class DatosCliente {

    public final static Cliente createCliente() {
        return Cliente
                .builder()
                .nombre("Carlos")
                .apellido("Borre")
                .email("cborre@gmail.com")
                .createAt(new Date())
                .build();
    }

    public final static ClienteDto createClienteDto() {
        return ClienteDto
                .builder()
                .id(1)
                .nombre("Carlos")
                .apellido("Borre")
                .email("cborre@gmail.com")
                .createAt(new Date())
                .build();
    }

    public final static Optional<Cliente> createClienteOptional() {
        return Optional.of(createCliente());
    }

    public final static Cliente updateCliente() {
        return Cliente
                .builder()
                .id(1)
                .nombre("Carlos")
                .apellido("Borre")
                .email("cborre@gmail.com")
                .createAt(new Date())
                .build();
    }

    public final static Optional<Cliente> updateClienteOptional() {
        return Optional.of(updateCliente());
    }

    public final static List<Cliente> datosCliente() {
        return Arrays.asList(
                Cliente.builder()
                        .id(1)
                        .nombre("Carlos")
                        .apellido("Borre")
                        .email("cborre@gmail.com")
                        .createAt(new Date())
                        .build(),
                Cliente.builder()
                        .id(2)
                        .nombre("Ines")
                        .apellido("Antero")
                        .email("ines.antero@gmail.com")
                        .createAt(new Date())
                        .build());
    }

    public final static List<ClienteDto> datosClienteDto() {
        return Arrays.asList(
                ClienteDto.builder()
                        .id(1)
                        .nombre("Carlos")
                        .apellido("Borre")
                        .email("cborre@gmail.com")
                        .createAt(new Date())
                        .build(),
                ClienteDto.builder()
                        .id(2)
                        .nombre("Ines")
                        .apellido("Antero")
                        .email("ines.antero@gmail.com")
                        .createAt(new Date())
                        .build());
    }

    public final static Page<Cliente> datosClientePage() {
        return new PageImpl<>(Arrays.asList(
                Cliente.builder()
                        .id(1)
                        .nombre("Carlos")
                        .apellido("Borre")
                        .email("cborre@gmail.com")
                        .createAt(new Date())
                        .build(),
                Cliente.builder()
                        .id(2)
                        .nombre("Ines")
                        .apellido("Antero")
                        .email("ines.antero@gmail.com")
                        .createAt(new Date())
                        .build()));
    }
}