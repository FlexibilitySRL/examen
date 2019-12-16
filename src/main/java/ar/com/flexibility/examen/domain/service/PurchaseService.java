package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.model.PurchaseItem;
import ar.com.flexibility.examen.domain.model.PurchaseStatus;
import ar.com.flexibility.examen.domain.repository.CustomerRepository;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.repository.PurchaseRepository;
import ar.com.flexibility.examen.domain.repository.SellerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Servicio que maneja la compra de productos
 *
 */
@Service
public class PurchaseService {
    private static final Logger logger = Logger.getLogger(ProductService.class);

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    public Purchase createNewPurchase(Integer sellerId, Integer customerId) {
        Purchase newPurchase = new Purchase();
        newPurchase.setSeller(sellerRepository.findOne(sellerId));
        newPurchase.setCustomer(customerRepository.findOne(customerId));
        try {
            newPurchase = purchaseRepository.saveAndFlush(newPurchase);
        } catch (Exception e) {
            logger.info("No se pudo guardar la compra.");
            return null;
        }

        logger.info("Nueva compra creada exitosamente.");
        return newPurchase;
    }

    public void addProductToPurchase(Integer purchaseId, Integer productId) {
        Purchase purchase = null;
        try {
            purchase = purchaseRepository.findOne(purchaseId);
        } catch (Exception e) {
            logger.info("No se encontro la compra para agregar el producto.");
            return;
        }

        try {
            PurchaseItem newItem = new PurchaseItem();
            newItem.setPurchase(purchase);
            newItem.setProduct(productRepository.findOne(productId));
            purchase.getItems().add(newItem);
            logger.info("Nueva item agregado exitosamente.");
        } catch (Exception e) {
            logger.info("El producto seleccionado no existe.");
        }

        purchaseRepository.saveAndFlush(purchase);
    }

    public void endPurchase(Integer purchaseId) {
        Purchase purchase = null;
        try {
            purchase = purchaseRepository.findOne(purchaseId);
            purchase.setStatus(PurchaseStatus.PENDING);

            purchaseRepository.saveAndFlush(purchase);
        } catch (Exception e) {
            logger.info("No se encontro la compra a finalizar.");
        }
    }

    public void changeStatusToPurchase(Integer purchaseId, PurchaseStatus newStatus) {
        Purchase purchase = null;
        try {
            purchase = purchaseRepository.findOne(purchaseId);
            purchase.setStatus(newStatus);
            purchaseRepository.save(purchase);
            logger.info("La compra cambio al estado " + newStatus +  " exitosamente.");
        } catch (Exception e) {
            logger.info("La compra que intenta cambiar de estado no existe.");
        }
    }

    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    public Purchase find(Integer id) {
        return purchaseRepository.findOne(id);
    }
}
