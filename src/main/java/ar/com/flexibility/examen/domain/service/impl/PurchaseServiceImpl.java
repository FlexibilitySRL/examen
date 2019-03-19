package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.api.PurchaseDto;
import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.model.Status;
import ar.com.flexibility.examen.domain.model.exception.EntityNotFoundException;
import ar.com.flexibility.examen.domain.model.filter.PurchaseFilter;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.repository.PurchaseRepository;
import ar.com.flexibility.examen.domain.repository.SellerRepository;
import ar.com.flexibility.examen.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository repository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Purchase create(PurchaseDto dto) {
        try {
            final Purchase purchase = validate(dto);
            purchase.setTransactionDate(Calendar.getInstance().getTime());
            purchase.setStatus(Status.IN_PROGRESS);
            return repository.save(purchase);
        } catch (final EntityNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Purchase> listWhere(PurchaseFilter filter) {
        return repository.findAll();
    }

    @Override
    public Purchase validate(long id) {
        final Purchase purchase = repository.findOne(id);
        purchase.setStatus(Status.COMPLETED);
        return repository.saveAndFlush(purchase);
    }

    private Purchase validate(PurchaseDto dto) throws EntityNotFoundException {
        final Purchase purchase = new Purchase();
        purchase.setClient(clientRepository.findOne(dto.getClientId()));
        purchase.setSeller(sellerRepository.findOne(dto.getSellerId()));
        purchase.setProducts(productRepository.findAll(dto.getProductsIds()));

        if(purchase.getClient() == null) throw new EntityNotFoundException("Client not exist");
        if(purchase.getSeller() == null) throw new EntityNotFoundException("Seller not exist");
        if(purchase.getProducts() == null || purchase.getProducts().isEmpty())
            throw new EntityNotFoundException("Products not exist");

        return purchase;
    }

}
