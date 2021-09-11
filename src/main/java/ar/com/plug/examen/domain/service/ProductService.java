package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.productModel;
import ar.com.plug.examen.domain.model.purchaseTransactionModel;
import ar.com.plug.examen.domain.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    productRepository productRepository;

    public List<productModel> getAllProducts() {
        return (List<productModel>) productRepository.findAll();
    }

    public productModel saveProduct(productModel product){
        return productRepository.save(product);
    }

    public productModel updateProduct(Integer id, productModel productModel) {

       productRepository.deleteById(id);
       productRepository.save(productModel);

       return productRepository.save(productModel);
    }

    public boolean deleteProduct(Integer id) {

        try
        {
            productRepository.deleteById(id);
            return true;
        }
        catch(Exception err)
        {
            return false;
        }
    }

}
