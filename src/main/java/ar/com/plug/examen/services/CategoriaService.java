package ar.com.plug.examen.services;
import ar.com.plug.examen.models.CategoriaModel;
import ar.com.plug.examen.repositories.CategoriaRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    
    @Autowired
    CategoriaRepository categoriaRepository;

    public ArrayList<CategoriaModel> obtenerCategorias() {
        return (ArrayList<CategoriaModel>) categoriaRepository.findAll();
    }

    public CategoriaModel guardarCategoria(CategoriaModel categoria) {
        return categoriaRepository.save(categoria);
    }

    public Optional<CategoriaModel> obtenerPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public boolean eliminarCategoria(Long id) {
    try {
        categoriaRepository.deleteById(id);
        return true;
    } catch (Exception e) {
        return false;
    }
    }
    
}