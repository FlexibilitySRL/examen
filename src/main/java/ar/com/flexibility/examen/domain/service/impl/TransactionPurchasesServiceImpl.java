package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.dto.ProductDTO;
import ar.com.flexibility.examen.domain.dto.TransactionPurchasesDTO;
import ar.com.flexibility.examen.domain.exception.PurchaseException;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.TransactionPurchases;
import ar.com.flexibility.examen.domain.repository.TransactionPurchasesRepository;
import ar.com.flexibility.examen.domain.service.TransactionPurchasesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionPurchasesServiceImpl implements TransactionPurchasesService {


    @Autowired
    private TransactionPurchasesRepository transactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TransactionPurchasesDTO create(TransactionPurchasesDTO transactionPurchasesDto) {

        if(existTransactionId(transactionPurchasesDto.getTransactionId())){
            throw new PurchaseException("Already exists transacionId: " + transactionPurchasesDto.getTransactionId());
        }

        TransactionPurchases transactionPurchases = convertToModel(transactionPurchasesDto);
        transactionPurchases =  transactionRepository.save(transactionPurchases);
        return convertToDTO(transactionPurchases);
    }

    @Override
    public TransactionPurchasesDTO findByTransaction(String transactionId) {
       return convertToDTO(transactionRepository.findByTransactionId(transactionId)
                                    .orElseThrow(() -> new EntityNotFoundException("No found transaction id: " + transactionId)));
    }

    public  boolean existTransactionId(String transactionId){

        if(transactionRepository.findByTransactionId(transactionId).isPresent()){
            return true;
        }
        return false;
    }


    protected TransactionPurchases convertToModel(TransactionPurchasesDTO transactionPurchasesDto) {
        List<Product> products = new ArrayList<Product>();
        Customer customer = new Customer();

        for(ProductDTO p: transactionPurchasesDto.getProduct()){
            Product pp = new Product();
            pp.setName(p.getName());
            pp.setPrice(p.getPrice());
            pp.setQuantity(p.getQuantity());
            products.add(pp);
        }

        customer.setFirstName(transactionPurchasesDto.getCustomer().getFirstName());
        customer.setLastName(transactionPurchasesDto.getCustomer().getLastName());
        customer.setDocumentNumber(transactionPurchasesDto.getCustomer().getDocumentNumber());
        customer.setBirthDate(transactionPurchasesDto.getCustomer().getBirthDate());



        TransactionPurchases transactionPurchases = new TransactionPurchases();
        transactionPurchases.setTransactionId(transactionPurchasesDto.getTransactionId());
        transactionPurchases.setCustomer(customer);
        transactionPurchases.setProduct(products);
        transactionPurchases.setTotal(transactionPurchasesDto.getTotal());
        transactionPurchases.setBuyDate(transactionPurchasesDto.getBuyDate());

        //TransactionPurchases transactionPurchases = modelMapper.map(transactionPurchasesDto, TransactionPurchases.class);
        return transactionPurchases;
    }

    public  TransactionPurchasesDTO convertToDTO(TransactionPurchases transactionPurchases) {
        TransactionPurchasesDTO transactionPurchasesDTO = modelMapper.map(transactionPurchases, TransactionPurchasesDTO.class);
        return transactionPurchasesDTO;
    }
}
