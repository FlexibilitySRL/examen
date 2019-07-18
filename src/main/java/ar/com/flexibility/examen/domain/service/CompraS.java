package ar.com.flexibility.examen.domain.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.entities.Compra;
import ar.com.flexibility.examen.domain.repository.CompraR;

@Service("CompraService")
public class CompraS {
    
    @Autowired
    @Qualifier("compraRep")
    CompraR compRep;
    

    
    public boolean agregar(Compra compra){
        try {
            compRep.save(compra);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean borrar(int id){
        try {
            Compra comp = compRep.findById(id);
            compRep.delete(comp);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean modificar(Compra compra){
        try {
            compRep.save(compra);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Compra> getAll(){
    		return compRep.findAll();
    }

    public Compra buscarID(int id){
        return new Compra(compRep.findById(id));
    }

    public List<Compra> buscarAprobado(boolean aprobado){
        return compRep.findByAprobado(aprobado).stream()
                .filter(a->a.isAprobado()==aprobado).collect(Collectors.toList());
    }


}
