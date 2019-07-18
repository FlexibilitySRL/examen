package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.entities.Cliente;
import ar.com.flexibility.examen.domain.repository.ClienteR;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("ClienteService")
public class ClienteS {

    @Autowired
    @Qualifier("clienteRep")
    private ClienteR clirep;


    public boolean agregar(Cliente cliente) {
        try {
            clirep.save(cliente);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean borrar(int id) {
        try {
            Cliente cli = clirep.findById(id);
            clirep.delete(cli);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean modificar(Cliente cliente) {
        try {
            clirep.save(cliente);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Cliente> getAll() {
        return clirep.findAll();
    }

    public Cliente buscarID(int id) {
        return new Cliente(clirep.findById(id));
    }

    public List<Cliente> buscarNombre(String nombre) {
        return clirep.findByNombre(nombre).stream()
                .filter(a->a.getNombre().contains(nombre))
                .collect(Collectors.toList());
    }
    
    public List<Cliente> buscarApellido(String apellido) {
        return clirep.findByApellido(apellido).stream()
            .filter(a->a.getApellido().contains(apellido))
            .collect(Collectors.toList());
    }

    public Cliente buscarNombreApellido(String nombre, String apellido) {
        return new Cliente(clirep.findByNombreAndApellido(nombre, apellido));
    }

}
