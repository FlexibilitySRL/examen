package ar.com.plug.examen.domain.serviceimpl;

import ar.com.plug.examen.domain.dtos.ClienteDTO;

import ar.com.plug.examen.domain.mappers.ClienteMapper;
import ar.com.plug.examen.domain.models.Cliente;
import ar.com.plug.examen.domain.repository.ClienteRepository;
import ar.com.plug.examen.domain.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteMapper clienteMapper;

    private final static Logger LOGGER = Logger.getLogger("domain.serviceimpl.ClienteServiceImpl");

    @Override
    public List<ClienteDTO> getAll() {
        try{
        return clienteRepository.findAll().stream().map(cliente -> clienteMapper.toDto(cliente)).collect(Collectors.toList());
        }catch (Exception e){
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    @Override
    public ClienteDTO save(ClienteDTO cliente) {
        try{
            return clienteMapper.toDto(clienteRepository.save(clienteMapper.toModel(cliente)));
        }catch (Exception e){
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(ClienteDTO cliente) {
        try{
            clienteRepository.delete(clienteMapper.toModel(cliente));
            LOGGER.info("Se borro el cliente "+cliente.getNombre()+" de la base de datos");
        }catch (Exception e){
            LOGGER.info(e.getMessage());
        }

    }

    @Override
    public List<ClienteDTO> getClienteByNombre(String nombre) {
        try{
            return clienteRepository.findByNombre(nombre).stream().map(cliente -> clienteMapper.toDto(cliente)).collect(Collectors.toList());
        }catch (Exception e){
            LOGGER.info(e.getMessage());
            return null;
    }
    }
}
