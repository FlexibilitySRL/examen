package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.constants.ErrorConstants;
import ar.com.plug.examen.domain.dto.SellerDTO;
import ar.com.plug.examen.domain.enums.Result;
import ar.com.plug.examen.domain.exception.CustomerNotFoundException;
import ar.com.plug.examen.domain.exception.ProductNotFoundException;
import ar.com.plug.examen.domain.exception.ProductParamException;
import ar.com.plug.examen.domain.model.LogTransation;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.LogTransationRepository;
import ar.com.plug.examen.domain.repository.PurchaseRepository;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.service.SellerService;
import ar.com.plug.examen.domain.util.LoggerExample;
import ar.com.plug.examen.domain.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ar.com.plug.examen.domain.constants.ErrorConstants.*;

@Service
public class SellerServiceImpl implements SellerService {
    private static final Logger LOGGER = Logger.getLogger(LoggerExample.class.getName());
    private static final String DOCUMENT_NUMBER = "document number";
    private static final String NAME = "name";
    private static final String LAST_NAME = "last name";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String ACTION_SAVE = "guardar vendedor";
    private static final String ACTION_EMPTY_DATA = "editar vendedor o eliminar vendedor";
    private static final String VALIDATE_DATA = "validación datos vendedor";
    private static final String ACTION_DELETE = "eliminar vendedor";
    private static final String ACTION_EDIT = "editar vendedor";
    public static final String ERROR_ID_SELLER = "Debe eliminar primero de compras el vendedor con id ";
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private LogTransationRepository logTransationRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Override
    public void createSeller(SellerDTO sellerDTO) {
        try {
        validateInputData(sellerDTO);
        existsDocumentNumber(sellerDTO.getDocumentNumber());
        Seller sellerToSave = new Seller();
        sellerToSave.setEmail(sellerDTO.getEmail());
        sellerToSave.setDocumentNumber(sellerDTO.getDocumentNumber());
        sellerToSave.setName(sellerDTO.getName());
        sellerToSave.setLastName(sellerDTO.getLastName());
        sellerToSave.setPhone(sellerDTO.getPhone());
        Seller sellerSave = sellerRepository.save(sellerToSave);

        StringBuilder description = new StringBuilder();
        description.append(SAVE_SUCCESS);
        description.append(" ");
        description.append(sellerSave.getIdSeller());
        createLog(ACTION_SAVE, Result.SUCCESS, description.toString());
        } catch (ExceptionInInitializerError ex) {
            createLog(ACTION_SAVE, Result.ERROR, ERROR_UNKNOW);
            LOGGER.log(Level.SEVERE, ErrorConstants.API_ERROR, ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteSeller(Long idSeller) {
       try{
        existsSeller(idSeller);
        existsRelationSeller(idSeller);
        sellerRepository.deleteById(idSeller);
        StringBuilder description = new StringBuilder();
        description.append(DELETE_SUCCESS);
        description.append(" ");
        description.append(idSeller);
        createLog(ACTION_DELETE, Result.SUCCESS, description.toString());
       } catch (ExceptionInInitializerError ex) {
           createLog(ACTION_DELETE, Result.ERROR, ERROR_UNKNOW);
           LOGGER.log(Level.SEVERE, ErrorConstants.API_ERROR, ex.getMessage());
           ex.printStackTrace();
       }
    }

    @Override
    public void editSeller(Long idSeller, SellerDTO sellerDTO) {
        try {
        validateInputData(sellerDTO);
        Optional<Seller> sellerResult = existsSeller(idSeller);
        sellerResult.get().setPhone(sellerDTO.getPhone());
        sellerResult.get().setName(sellerDTO.getName());
        sellerResult.get().setLastName(sellerDTO.getLastName());
        sellerResult.get().setEmail(sellerDTO.getEmail());
        sellerResult.get().setDocumentNumber(sellerDTO.getDocumentNumber());
        sellerRepository.saveAndFlush(sellerResult.get());
        StringBuilder description = new StringBuilder();
        description.append(EDIT_SUCCESS);
        description.append(" ");
        description.append(idSeller);
        createLog(ACTION_EDIT, Result.SUCCESS, description.toString());
        } catch (ExceptionInInitializerError ex) {
            createLog(ACTION_EDIT, Result.ERROR, ERROR_UNKNOW);
            LOGGER.log(Level.SEVERE, ErrorConstants.API_ERROR, ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void validateInputData(SellerDTO sellerDTO) {
        List<String> param = new ArrayList<>();

        if (Util.isBlank(sellerDTO.getName())) {
            param.add(NAME);
        }
        if (Util.isBlank(sellerDTO.getLastName())) {
            param.add(LAST_NAME);
        }
        if (Util.isBlank(sellerDTO.getDocumentNumber())) {
            param.add(DOCUMENT_NUMBER);
        }
        if (Util.isBlank(sellerDTO.getEmail())) {
            param.add(EMAIL);
        }
        if (Util.isBlank(sellerDTO.getPhone())) {
            param.add(PHONE);
        }
        if (!param.isEmpty()) {
            createLog(VALIDATE_DATA, Result.ERROR, ERROR_DATA_EMPTY);
            throw new ProductParamException(INVALID_PRODUCT_FIELD, param);
        }
    }
    private void existsDocumentNumber(String documentNumber) {
        if (sellerRepository.findSellerBydocumentNumber(documentNumber) != null) {
            StringBuilder description = new StringBuilder();
            description.append(ERROR_EXIST_SELLER);
            description.append(" ");
            description.append(documentNumber);
            createLog(ACTION_SAVE, Result.ERROR, description.toString());
            throw new ProductNotFoundException();
        }
    }


    private Optional<Seller> existsSeller(long idSeller) {
        Optional<Seller> sellerResult = sellerRepository.findById(idSeller);
        if (!sellerResult.isPresent()) {
            StringBuilder description = new StringBuilder();
            description.append(ERROR_EMPTY_ID);
            description.append(" ");
            description.append(idSeller);
            createLog(ACTION_EMPTY_DATA, Result.ERROR, description.toString());
            throw new CustomerNotFoundException();
        }
        return sellerResult;
    }
    private List<Purchase> existsRelationSeller(long idSeller) {
        List<Purchase> purchaseResult = purchaseRepository.findProductByCustomerIdCustomerOrProductIdProductOrSellerIdSeller(null,null, idSeller);
        if (!purchaseResult.isEmpty()) {
            StringBuilder description = new StringBuilder();
            description.append(ERROR_ID_SELLER);
            description.append(" ");
            description.append(idSeller);
            createLog(ACTION_DELETE, Result.ERROR, description.toString());
            throw new ProductNotFoundException();
        }
        return purchaseResult;
    }
    private void createLog(String action, Result result, String description) {
        LogTransation logTransation = LogTransation.builder()
                .module(action)
                .Result(result)
                .description(description).build();
        logTransationRepository.save(logTransation);
    }
}
