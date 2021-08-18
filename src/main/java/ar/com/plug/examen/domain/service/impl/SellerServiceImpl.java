package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.converter.SellerConverter;
import ar.com.plug.examen.domain.dto.Seller;
import ar.com.plug.examen.domain.model.SellerModel;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.service.SellerService;
import ar.com.plug.examen.objects.JsonResponseTransaction;
import ar.com.plug.examen.objects.StatusTransaction;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class SellerServiceImpl implements SellerService {

    private static final Log log = LogFactory.getLog(SellerServiceImpl.class);

    @Autowired
    private final SellerRepository sellerRepository;

    @Autowired
    private final SellerConverter sellerConverter;

    private static final String LOG_CONSTANT="Call: seller ";

    @Override
    public JsonResponseTransaction addSeller(SellerModel sellerModel) {
        log.info(LOG_CONSTANT + " addSellerModel");
        Seller seller = sellerRepository.save(sellerConverter.convertFromEntity(sellerModel));
        JsonResponseTransaction jsonResponseTransaction = new JsonResponseTransaction();
        jsonResponseTransaction = validateStatus(sellerModel, jsonResponseTransaction);
        log.info(jsonResponseTransaction.getResponseMessage());
        jsonResponseTransaction.setSellerModel(sellerConverter.convertFromModel(seller));
        jsonResponseTransaction.setStatusTransaction(StatusTransaction.fromId(sellerModel.getIdStatus()));
        return jsonResponseTransaction;
    }

    @Override
    public JsonResponseTransaction deleteSeller(Long id) {
        log.info(LOG_CONSTANT + " deleteSeller " + id);
        JsonResponseTransaction jsonResponseTransaction = new JsonResponseTransaction();
        sellerRepository.deleteById(id);
        jsonResponseTransaction.setResponseMessage("Seller "+id + " eliminated of system");
        log.info(jsonResponseTransaction.getResponseMessage());
        return jsonResponseTransaction;
    }

    @Override
    public JsonResponseTransaction updateSeller(SellerModel sellerModel) {
        log.info(LOG_CONSTANT + " updateSellerModel " + " before: " + sellerConverter.convertFromModel(sellerRepository.findById(sellerModel.getId()))
                + System.lineSeparator() + " sellerModel after: " + sellerModel);

        JsonResponseTransaction jsonResponseTransaction = new JsonResponseTransaction();
        jsonResponseTransaction = validateStatus(sellerModel, jsonResponseTransaction);
        log.info(jsonResponseTransaction.getResponseMessage());
        Seller seller = sellerRepository.save(sellerConverter.convertFromEntity(sellerModel));
        jsonResponseTransaction.setSellerModel(sellerConverter.convertFromModel(seller));
        jsonResponseTransaction.setStatusTransaction(StatusTransaction.fromId(sellerModel.getIdStatus()));
        return jsonResponseTransaction;
    }

    private JsonResponseTransaction validateStatus(SellerModel sellerModel, JsonResponseTransaction jsonResponseTransaction) {
        if (Objects.equals(sellerModel.getIdStatus(), StatusTransaction.INACTIVE.getId())) {
            jsonResponseTransaction.setResponseMessage("Seller add with successfully but status is inactive");
        }
        if (Objects.equals(sellerModel.getIdStatus(), StatusTransaction.ACTIVE.getId())) {
            jsonResponseTransaction.setResponseMessage("Seller add with successfully");
        }
        return jsonResponseTransaction;
    }

}
