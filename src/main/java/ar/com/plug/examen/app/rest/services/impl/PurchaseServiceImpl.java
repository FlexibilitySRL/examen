package ar.com.plug.examen.app.rest.services.impl;


import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.PurchaseDto;
import ar.com.plug.examen.app.rest.model.Client;
import ar.com.plug.examen.app.rest.model.Product;
import ar.com.plug.examen.app.rest.model.Purchase;
import ar.com.plug.examen.app.rest.model.Seller;
import ar.com.plug.examen.app.rest.repositories.ClientRepository;
import ar.com.plug.examen.app.rest.repositories.ProductRepository;
import ar.com.plug.examen.app.rest.repositories.PurchaseRepository;
import ar.com.plug.examen.app.rest.repositories.SellerRepository;
import ar.com.plug.examen.app.rest.services.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.ValidationException;
import java.util.*;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService
{
    private final PurchaseRepository purchaseRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public PurchaseServiceImpl(
            PurchaseRepository purchaseRepository, ClientRepository clientRepository,
            ProductRepository productRepository, SellerRepository sellerRepository)
    {
        this.purchaseRepository = purchaseRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }


    @Override
    public PageDto<Purchase> getApprovedPurchasesPageable(int pageNumber, int pageSize)
    {
        return new PageDto<>(
                this.purchaseRepository.findAllByApprovedTrue(PageRequest.of(pageNumber, pageSize))
        );
    }

    @Override
    public PageDto<Purchase> getAllPurchasesPageable(int pageNumber, int pageSize)
    {
        return new PageDto<>(
                this.purchaseRepository.findAll(PageRequest.of(pageNumber, pageSize))
        );
    }

    @Override
    public Purchase getPurchaseById(Long id)
    {
        logger.info(String.format("getPurchaseById"));

        if(Objects.isNull(id)) {
            logger.error(String.format("getPurchaseById - FAILED"));
            throw new NoSuchElementException("El id de la compra no puede ser nulo.");
        }
        Purchase purchase = this.purchaseRepository.findById(id).orElseThrow(()->{
            logger.error(String.format("getPurchaseById %d - FAILED",id));
            throw new NoSuchElementException("Purchase Was not found.");
        });

        logger.info(String.format("getPurchaseById %d - SUCCESS",id));
        return purchase;
    }

    @Override
    public Purchase savePurchase(PurchaseDto purchaseDto) throws ValidationException
    {
        if(Objects.isNull(purchaseDto)) {
            throw new ValidationException("data cannot be empty.");
        }
        Purchase purchase = new Purchase();
        Seller seller;
        Client client ;
        Product product = new Product();

        seller = this.sellerRepository.findById(purchaseDto.getSellerId()).orElseThrow(()->new NoSuchElementException("Seller With Id " + purchaseDto.getSellerId()+ " doesn't exist"));
        purchase.setSeller(seller);


        client = this.clientRepository.findById(purchaseDto.getClientId()).orElseThrow(()->new NoSuchElementException("Client With Id " + purchaseDto.getClientId()+ " doesn't exist"));
        purchase.setClient(client);

        if(purchaseDto.getId()!=null){
            purchase = this.purchaseRepository.findById(purchaseDto.getId()).orElseThrow(()->new NoSuchElementException("Purchase With Id " + purchaseDto.getId() + " doesn't exist"));
            if(purchase.getApproved()) throw  new ValidationException("You cannot modify an approved purchase");
            if(purchase.getProduct().getId()!=purchaseDto.getProductId()) {
                Purchase finalPurchase = purchase;
                product = this.productRepository.findById(purchase.getProduct().getId()).orElseThrow(()->new NoSuchElementException("Product With Id " + finalPurchase.getProduct().getId() + " doesn't exist"));
                product.setStock(product.getStock() +finalPurchase.getQuantity());
                this.productRepository.save(product);

                product = this.productRepository.findById(purchaseDto.getProductId()).orElseThrow(()->new NoSuchElementException("Product With Id " + purchaseDto.getProductId()+ " doesn't exist"));
                product.setStock(product.getStock() - purchaseDto.getQuantity());
                purchase.setProduct(product);
                this.productRepository.save(product);
            }
            return this.purchaseRepository.save(purchase);
        }else{
            product = this.productRepository.findById(purchaseDto.getProductId()).orElseThrow(()->new NoSuchElementException("Product With Id " + purchaseDto.getProductId()+ " doesn't exist"));
            product.setStock(product.getStock() - purchaseDto.getQuantity());
            purchase.setProduct(product);
            this.productRepository.save(product);
            purchase.setQuantity(purchaseDto.getQuantity());
            purchase.setTotal(purchaseDto.getTotal());
            purchase.setApproved(false);
            return this.purchaseRepository.save(purchase);
        }
    }

    @Override
    public Purchase approvePurchase(Long id) throws ValidationException
    {
        if(Objects.isNull(id)) {
            throw new ValidationException("Los datos para la actualización de la compra no pueden ser nulos.");
        }
        Purchase purchaseFromDatabase = this.purchaseRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("La compra con el id " + id + " no existe.")
                );
        purchaseFromDatabase.setApproved(Boolean.TRUE);
        return this.purchaseRepository.save(purchaseFromDatabase);
    }
}
