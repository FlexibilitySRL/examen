package ar.com.plug.examen.domain.mappers;

import ar.com.plug.examen.domain.dtos.ClienteDTO;
import ar.com.plug.examen.domain.models.Cliente;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ClienteMapper {

    private final static Logger LOGGER = Logger.getLogger("domain.serviceimpl.ClienteMapper");

    public ClienteDTO toDto(Cliente cliente){
        LOGGER.info("iniciando mapeo a dto");
        ClienteDTO dto = new ClienteDTO();
        dto.setApellido(cliente.getApellido());
        dto.setNombre(cliente.getNombre());
        dto.setDireccion(cliente.getDireccion());
        dto.setDocumento(cliente.getDocumento());
        dto.setId(cliente.getId());
        dto.setNumCliente(cliente.getNumCliente());
        dto.setTelefono(cliente.getTelefono());

        return dto;
    }

    public Cliente toModel(ClienteDTO clienteDTO){
        LOGGER.info("iniciando mapeo a modelo");
        Cliente model = new Cliente();
        model.setApellido(clienteDTO.getApellido());
        model.setNombre(clienteDTO.getNombre());
        model.setDireccion(clienteDTO.getDireccion());
        model.setDocumento(clienteDTO.getDocumento());
        model.setId(clienteDTO.getId());
        model.setNumCliente(clienteDTO.getNumCliente());
        model.setTelefono(clienteDTO.getTelefono());

        return model;
    }
}
