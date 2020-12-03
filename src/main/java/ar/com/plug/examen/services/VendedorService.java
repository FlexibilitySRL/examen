package ar.com.plug.examen.services;
import ar.com.plug.examen.models.VendedorModel;
import ar.com.plug.examen.repositories.VendedorRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class VendedorService {
    
    @Autowired
    VendedorRepository vendedorRepository;

    public ArrayList<VendedorModel> obtenerVendedores() {
        return (ArrayList<VendedorModel>) vendedorRepository.findAll();
    }

    public VendedorModel guardarVendedor(VendedorModel vendedor) {
        return vendedorRepository.save(vendedor);
    }

    public Optional<VendedorModel> obtenerPorId(Long id) {
        return vendedorRepository.findById(id);
    }

    public boolean eliminarVendedor(Long id) {
    try {
        vendedorRepository.deleteById(id);
        return true;
    } catch (Exception e) {
        return false;
    }
    }
    
}