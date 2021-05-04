package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.datasource.model.BaseModel;
import ar.com.plug.examen.datasource.model.Purchase;
import ar.com.plug.examen.datasource.repo.PurchaseRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProcessPurchaseServiceImpl extends AbstractBaseModelService<PurchaseRepo, Purchase> {

    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public ProcessPurchaseServiceImpl(PurchaseRepo repo) {
        super(repo);
    }

    public void approve(Long id) {
        Purchase purchase = repo.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Purchase with Id: %s not found", id)));
        purchase.setApproved(true);
        final Purchase save = repo.save(purchase);
        log.info(getDomainClass().getSimpleName() + " approved with id: " + save.getId());
    }

    public List<Purchase> findAllByCreationDateTimeBetween(String startDate, String endDate) {
        try {
            final List<Purchase> allByCreationDateTimeBetween = repo.findAllByCreationDateTimeBetween(simpleDateFormat.parse(startDate), simpleDateFormat.parse(endDate));
            log.info(getDomainClass().getSimpleName() + " found with ids: " + allByCreationDateTimeBetween.stream().map(BaseModel::getId).collect(Collectors.toList()));
            return allByCreationDateTimeBetween;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date", e);
        }
    }

    @Override
    Class<Purchase> getDomainClass() {
        return Purchase.class;
    }


}
