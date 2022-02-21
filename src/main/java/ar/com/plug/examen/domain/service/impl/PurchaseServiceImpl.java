package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.constants.ErrorConstants;
import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.dto.PurchaseDTO;
import ar.com.plug.examen.domain.enums.PurchaseState;
import ar.com.plug.examen.domain.exception.*;
import ar.com.plug.examen.domain.model.*;
import ar.com.plug.examen.domain.repository.*;
import ar.com.plug.examen.domain.service.PurchaseService;
import ar.com.plug.examen.domain.util.LoggerExample;
import ar.com.plug.examen.domain.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static ar.com.plug.examen.domain.constants.ErrorConstants.INVALID_PRODUCT_FIELD;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private static final Logger LOGGER = Logger.getLogger(LoggerExample.class.getName());
    private static final String VOUCHER = "voucher";
    private static final String TAXES = "taxes";
    private static final String AMOUNT = "amount";
    private static final String CUSTOMER = "customer";
    private static final String SELLER = "seller";
    private static final String PRODUCT = "product";
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private PurchaseDetailRepository purchaseDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void createPurchase(PurchaseDTO purchaseDTO) {
        try {
            validateInputData(purchaseDTO);
            existsCustomer(purchaseDTO.getCustomer().getIdCustomer());
            existsSeller(purchaseDTO.getSeller().getIdSeller());
            existsProduct(purchaseDTO.getProduct().getIdProduct());
            Purchase purchaseToSave = new Purchase();
            purchaseToSave.setCustomer(purchaseDTO.getCustomer());
            purchaseToSave.setSeller(purchaseDTO.getSeller());
            purchaseToSave.setProduct(purchaseDTO.getProduct());
            purchaseToSave.setVoucher(purchaseDTO.getVoucher());
            purchaseToSave.setTaxes(purchaseDTO.getTaxes());
            purchaseToSave.setAmount(purchaseDTO.getAmount());
            purchaseRepository.save(purchaseToSave);

            PurchaseDetail purchaseDetail = PurchaseDetail.builder()
                    .purchase(purchaseToSave)
                    .purchaseState(PurchaseState.SOLICITADA).build();
            purchaseDetailRepository.save(purchaseDetail);
        } catch (ExceptionInInitializerError ex) {
            LOGGER.log(Level.SEVERE, ErrorConstants.API_ERROR, ex.getMessage());
            ex.printStackTrace();
        } finally {

        }

    }

    private Optional<Customer> existsCustomer(long idCustomer) {
        Optional<Customer> customerResult = customerRepository.findById(idCustomer);
        if (!customerResult.isPresent()) {
            throw new CustomerNotFoundException();
        }
        return customerResult;
    }

    private Optional<Seller> existsSeller(long idSeller) {
        Optional<Seller> sellerResult = sellerRepository.findById(idSeller);
        if (!sellerResult.isPresent()) {
            throw new SellerNotFoundException();
        }
        return sellerResult;
    }

    private Optional<Product> existsProduct(long idProduct) {
        Optional<Product> productResult = productRepository.findById(idProduct);
        if (!productResult.isPresent()) {
            throw new ProductNotFoundException();
        }
        return productResult;
    }

    @Override
    public List<PurchaseDTO> listPurchase() {
        return purchaseRepository.findAll()
                .stream()
                .map(PurchaseDTO::fromModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void approvePurchase(long idPurchase) {
        try {
            Optional<Purchase> purchaseResult = purchaseRepository.findById(idPurchase);
            purchaseResult.get().setIdPurchase(idPurchase);
            purchaseResult.get().setUpdateDate(LocalDateTime.now());
            purchaseRepository.save(purchaseResult.get());

            PurchaseDetail purchaseDetail = PurchaseDetail.builder()
                    .purchaseState(PurchaseState.APROBADA)
                    .purchase(purchaseResult.get()).build();
            purchaseDetailRepository.save(purchaseDetail);
        } catch (ExceptionInInitializerError ex) {
            LOGGER.log(Level.SEVERE, ErrorConstants.API_ERROR, ex.getMessage());
            ex.printStackTrace();
        } finally {

        }
    }

    private void validateInputData(PurchaseDTO purchaseDTO) {
        List<String> param = new ArrayList<>();

        if (Util.isBlank(purchaseDTO.getVoucher())) {
            param.add(VOUCHER);
        }
        if (null == purchaseDTO.getTaxes()) {
            param.add(TAXES);
        }
        if (null == purchaseDTO.getAmount()) {
            param.add(AMOUNT);
        }
        if (null == purchaseDTO.getSeller()) {
            param.add(SELLER);
        }
        if (null == purchaseDTO.getCustomer()) {
            param.add(CUSTOMER);
        }
        if (null == purchaseDTO.getProduct()) {
            param.add(PRODUCT);
        }
        if (!param.isEmpty()) {
            throw new PurchaseParamException(INVALID_PRODUCT_FIELD, param);
        }
    }

}
