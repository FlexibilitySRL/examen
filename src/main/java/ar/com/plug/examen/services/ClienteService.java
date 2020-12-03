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

    public ArrayList<ClienteModel> obtenerUsuario() {
        return (ArrayList<ClienteModel>) clienteRepository.findAll();
    }

    public ClienteModel guardarUsuario(ClienteModel usuario) {
        return clienteRepository.save(usuario);
    }

    public Optional<ClienteModel> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public boolean eliminarUsuario(Long id) {
    try {
        clienteRepository.deleteById(id);
        return true;
    } catch (Exception e) {
        return false;
    }
    }
    
}