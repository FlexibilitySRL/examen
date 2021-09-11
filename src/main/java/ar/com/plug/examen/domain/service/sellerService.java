package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.clientModel;
import ar.com.plug.examen.domain.model.productModel;
import ar.com.plug.examen.domain.model.sellerModel;
import ar.com.plug.examen.domain.repository.sellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class sellerService {

    @Autowired
    sellerRepository sellerRepository;

    public List<sellerModel> getAllSellers() {
        return (List<sellerModel>) sellerRepository.findAll();
    }

    public sellerModel saveSeller(sellerModel seller){
        return sellerRepository.save(seller);
    }

    public boolean deleteSeller(Integer id) {

        try
        {
            sellerRepository.deleteById(id);
            return true;
        }
        catch(Exception err)
        {
            return false;
        }

    }
}
