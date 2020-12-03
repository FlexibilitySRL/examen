package ar.com.plug.examen.services;
import ar.com.plug.examen.models.ClienteModel;
import ar.com.plug.examen.repositories.ClienteRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    
    @Autowired
    ClienteRepository clienteRepository;

    public ArrayList<ClienteModel> obtenerClientes() {
        return (ArrayList<ClienteModel>) clienteRepository.findAll();
    }

    public ClienteModel guardarCliente(ClienteModel cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<ClienteModel> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public boolean eliminarCliente(Long id) {
    try {
        clienteRepository.deleteById(id);
        return true;
    } catch (Exception e) {
        return false;
    }
    }
    
}