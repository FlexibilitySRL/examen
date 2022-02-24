package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.constants.ErrorConstants;
import ar.com.plug.examen.domain.dto.PurchaseDTO;
import ar.com.plug.examen.domain.enums.PurchaseState;
import ar.com.plug.examen.domain.enums.Result;
import ar.com.plug.examen.domain.exception.*;
import ar.com.plug.examen.domain.model.*;
import ar.com.plug.examen.domain.repository.*;
import ar.com.plug.examen.domain.service.PurchaseService;
import ar.com.plug.examen.domain.util.LoggerExample;
import ar.com.plug.examen.domain.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static ar.com.plug.examen.domain.constants.ErrorConstants.*;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private static final Logger LOGGER = Logger.getLogger(LoggerExample.class.getName());
    private static final String VOUCHER = "voucher";
    private static final String TAXES = "taxes";
    private static final String AMOUNT = "amount";
    private static final String CUSTOMER = "customer";
    private static final String SELLER = "seller";
    private static final String PRODUCT = "product";
    private static final String ACTION_SAVE = "guardar compra";
    private static final String GET_PURCHASE = "consulta compras";
    private static final String ACTION_APPROVE_PURCHASE = "aprobar compra";
    private static final String DETAIL_PURCHASE = "Tambien se registro en detalle compra";
    private static final String VALIDATE_DATA = "validación datos";
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

    @Autowired
    private LogTransationRepository logTransationRepository;

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
            Purchase PurchaseSave = purchaseRepository.saveAndFlush(purchaseToSave);

            PurchaseDetail purchaseDetail = PurchaseDetail.builder()
                    .purchase(purchaseToSave)
                    .purchaseState(PurchaseState.SOLICITADA).build();
            purchaseDetailRepository.saveAndFlush(purchaseDetail);

            StringBuilder description = new StringBuilder();
            description.append(SAVE_SUCCESS);
            description.append(" ");
            description.append(PurchaseSave.getIdPurchase());
            description.append(" ");
            description.append(DETAIL_PURCHASE);
            createLog(ACTION_SAVE, Result.SUCCESS, description.toString());
        } catch (ExceptionInInitializerError ex) {
            createLog(ACTION_SAVE, Result.ERROR, ERROR_UNKNOW);
            LOGGER.log(Level.SEVERE, ErrorConstants.API_ERROR, ex.getMessage());
            ex.printStackTrace();
        }

    }

    private Optional<Customer> existsCustomer(long idCustomer) {
        Optional<Customer> customerResult = customerRepository.findById(idCustomer);
        if (!customerResult.isPresent()) {
            StringBuilder description = new StringBuilder();
            description.append(ERROR_EMPTY_ID);
            description.append(" ");
            description.append(idCustomer);
            createLog(ACTION_SAVE, Result.ERROR, description.toString());
            throw new CustomerNotFoundException();
        }
        return customerResult;
    }

    private Optional<Seller> existsSeller(long idSeller) {
        Optional<Seller> sellerResult = sellerRepository.findById(idSeller);
        if (!sellerResult.isPresent()) {
            StringBuilder description = new StringBuilder();
            description.append(ERROR_EMPTY_ID);
            description.append(" ");
            description.append(idSeller);
            createLog(ACTION_SAVE, Result.ERROR, description.toString());
            throw new SellerNotFoundException();
        }
        return sellerResult;
    }

    private Optional<Product> existsProduct(long idProduct) {
        Optional<Product> productResult = productRepository.findById(idProduct);
        if (!productResult.isPresent()) {
            StringBuilder description = new StringBuilder();
            description.append(ERROR_EMPTY_ID);
            description.append(" ");
            description.append(idProduct);
            createLog(ACTION_SAVE, Result.ERROR, description.toString());
            throw new ProductNotFoundException();
        }
        return productResult;
    }

    @Override
    public List<PurchaseDTO> listPurchase() {
        createLog(GET_PURCHASE, Result.SUCCESS, GET_PURCHASE_BD);
        return purchaseRepository.findAll()
                .stream()
                .map(PurchaseDTO::fromModelToDto).collect(Collectors.toList());

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
            StringBuilder description = new StringBuilder();
            description.append(APPROVE_PURCHASE);
            description.append(idPurchase);
            createLog(ACTION_APPROVE_PURCHASE, Result.SUCCESS, description.toString());
        } catch (ExceptionInInitializerError ex) {
            createLog(ACTION_SAVE, Result.ERROR, ERROR_UNKNOW);
            LOGGER.log(Level.SEVERE, ErrorConstants.API_ERROR, ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void validateInputData(PurchaseDTO purchaseDTO) {
        boolean isError = false;
        if (Util.isBlank(purchaseDTO.getVoucher())) {
            isError = true;
            throw new PurchaseParamException(VOUCHER);
        }
        if (null == purchaseDTO.getTaxes()) {
            isError = true;
            throw new PurchaseParamException(TAXES);
        }
        if (null == purchaseDTO.getAmount()) {
            isError = true;
            throw new PurchaseParamException(AMOUNT);
        }
        if (null == purchaseDTO.getSeller()) {
            isError = true;
            throw new PurchaseParamException(SELLER);
        }
        if (null == purchaseDTO.getCustomer()) {
            isError = true;
            throw new PurchaseParamException(CUSTOMER);

        }
        if (null == purchaseDTO.getProduct()) {
            isError = true;
            throw new PurchaseParamException(PRODUCT);

        }
        if (isError) {
            createLog(VALIDATE_DATA, Result.ERROR, ERROR_DATA_EMPTY);
        }
    }

    private void createLog(String action, Result result, String description) {
        LogTransation logTransation = LogTransation.builder()
                .module(action)
                .Result(result)
                .description(description).build();
        logTransationRepository.save(logTransation);
    }

}
