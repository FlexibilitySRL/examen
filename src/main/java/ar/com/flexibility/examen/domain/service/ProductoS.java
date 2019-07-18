package ar.com.flexibility.examen.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.entities.Producto;
import ar.com.flexibility.examen.domain.repository.ProductoR;

@Service("ProductoService")
public class ProductoS {

    @Autowired
    @Qualifier("productoRep")
    private ProductoR prodRep;
    
    public boolean agregar(Producto producto){
        try {
            prodRep.save(producto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean borrar(int id){
        try {
            Producto prod = prodRep.findById(id);
            prodRep.delete(prod);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean modificar(Producto producto){
        try {
            prodRep.save(producto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Producto> getAll(){
        return prodRep.findAll();
    }

    public Producto buscarID(int id){
        return new Producto(prodRep.findById(id));
    }
    
    public Producto buscarNombre(String nombre){
        return new Producto(prodRep.findByNombre(nombre));
    }

    public List<Producto> buscarCategoria(String categoria){
        return prodRep.findByCategoria(categoria).stream()
            .filter(a->a.getCategoria().contains(categoria)).collect(Collectors.toList());
    }
}
