package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.repository.ClienteRepository;
import ar.com.plug.examen.domain.service.ClienteService;
import ar.com.plug.examen.dto.requests.ClienteRequest;
import ar.com.plug.examen.dto.responses.ClienteResponse;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ResponseEntity addCliente(ClienteRequest clienteRequest) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteRequest.getNombre());
        cliente.setApellido(clienteRequest.getApellido());
        cliente.setDireccion(clienteRequest.getDireccion());
        cliente.setEmail(clienteRequest.getEmail());
        cliente.setDni(clienteRequest.getDni());
        cliente.setTelefono(clienteRequest.getTelefono());
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    @Override
    public ResponseEntity deleteCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
    if (Objects.nonNull(cliente)) {
        clienteRepository.delete(cliente);
        return ResponseEntity.ok().build();
    }
    else {
        throw new RuntimeException("El cliente con identificador " + id + "no ha sido encontrado.");
    }
    }

    @Override
    public ResponseEntity updateCliente(ClienteRequest clienteRequest, Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (Objects.nonNull(cliente)) {
            cliente.setNombre(clienteRequest.getNombre());
            cliente.setApellido(clienteRequest.getApellido());
            cliente.setDireccion(clienteRequest.getDireccion());
            cliente.setEmail(clienteRequest.getEmail());
            cliente.setDni(clienteRequest.getDni());
            cliente.setTelefono(clienteRequest.getTelefono());
            return ResponseEntity.ok(clienteRepository.save(cliente));        }
        else {
            throw new RuntimeException("El cliente con identificador " + id + "no ha sido encontrado.");
        }
    }

    @Override
    public List<ClienteResponse> listClientes() {
        ClienteResponse clienteResponse = new ClienteResponse();
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteResponse> clienteResponseList =
        clientes.stream().map(cliente -> clienteResponse.builder()
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .telefono(cliente.getTelefono())
                .direccion(cliente.getDireccion())
                .email(cliente.getEmail())
                .dni(cliente.getDni()).build()).collect(Collectors.toList());
        return clienteResponseList;
    }
}
