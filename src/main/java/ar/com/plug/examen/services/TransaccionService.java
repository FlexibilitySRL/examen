package ar.com.plug.examen.services;
import ar.com.plug.examen.models.TransaccionModel;
import ar.com.plug.examen.repositories.TransaccionRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class TransaccionService {
    
    @Autowired
    TransaccionRepository transaccionRepository;

    public ArrayList<TransaccionModel> obtenerTransacciones() {
        return (ArrayList<TransaccionModel>) transaccionRepository.findAll();
    }

    public TransaccionModel guardarTransaccion(TransaccionModel transaccion) {
        return transaccionRepository.save(transaccion);
    }

    public TransaccionModel actualizarEstadoTransaccion(TransaccionModel transaccion) {
        return transaccionRepository.save(transaccion);
    }

    public Optional<TransaccionModel> obtenerPorId(Long id) {
        return transaccionRepository.findById(id);
    }

    public boolean eliminarTransaccion(Long id) {
    try {
        transaccionRepository.deleteById(id);
        return true;
    } catch (Exception e) {
        return false;
    }
    }
    
}