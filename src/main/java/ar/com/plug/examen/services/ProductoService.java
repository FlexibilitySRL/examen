package ar.com.plug.examen.services;
import ar.com.plug.examen.models.ProductoModel;
import ar.com.plug.examen.repositories.ProductoRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    
    @Autowired
    ProductoRepository productoRepository;

    public ArrayList<ProductoModel> obtenerProductos() {
        return (ArrayList<ProductoModel>) productoRepository.findAll();
    }

    public ProductoModel guardarProductos(ProductoModel productos) {
        return productoRepository.save(productos);
    }

    public Optional<ProductoModel> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    public boolean eliminarProductos(Long id) {
    try {
        productoRepository.deleteById(id);
        return true;
    } catch (Exception e) {
        return false;
    }
    }
    
}