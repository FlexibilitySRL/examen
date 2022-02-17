package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.dto.PurchaseDTO;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.repository.PurchaseRepository;
import ar.com.plug.examen.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public void createPurchase(PurchaseDTO purchaseDTO) {
        Purchase purchaseToSave = new Purchase();
        purchaseToSave.getCustomer().setIdCustomer(1);
        purchaseToSave.getSeller().setIdSeller(1);
        purchaseToSave.setVoucher(purchaseDTO.getVoucher());
        purchaseToSave.setTaxes(purchaseDTO.getTaxes());
        purchaseToSave.setAmount(purchaseDTO.getAmount());
        purchaseRepository.save(purchaseToSave);
    }
}
