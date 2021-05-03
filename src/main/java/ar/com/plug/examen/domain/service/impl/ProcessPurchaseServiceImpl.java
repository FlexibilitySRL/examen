package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.datasource.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProcessPurchaseServiceImpl extends AbstractIdModelService<Purchase> {

    public ProcessPurchaseServiceImpl(JpaRepository<Purchase, Long> repo) {
        super(repo);
    }

    @Override
    Class<Purchase> getDomainClass() {
        return Purchase.class;
    }
}
