package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.dto.PurchaseDTO;
import ar.com.plug.examen.domain.enums.PurchaseState;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.PurchaseDetail;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.domain.repository.PurchaseDetailRepository;
import ar.com.plug.examen.domain.repository.PurchaseRepository;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private PurchaseDetailRepository purchaseDetailRepository;

    @Override
    public void createPurchase(PurchaseDTO purchaseDTO) {
        Optional<Customer> customerResult = existsCustomer(purchaseDTO.getCustomer().getIdCustomer());
        Optional<Seller> sellerResult =  existsSeller(purchaseDTO.getSeller().getIdSeller());
         if(!customerResult.isPresent() && !sellerResult.isPresent()){
             System.out.println("excepciones");
         }

        Purchase purchaseToSave = new Purchase();
        purchaseToSave.setCustomer(purchaseDTO.getCustomer());
        purchaseToSave.setSeller(purchaseDTO.getSeller());
        purchaseToSave.setVoucher(purchaseDTO.getVoucher());
        purchaseToSave.setTaxes(purchaseDTO.getTaxes());
        purchaseToSave.setAmount(purchaseDTO.getAmount());
        purchaseRepository.save(purchaseToSave);

        PurchaseDetail purchaseDetail = PurchaseDetail.builder()
                .purchase(purchaseToSave)
                .purchaseState(PurchaseState.SOLICITADA).build();
        purchaseDetailRepository.save(purchaseDetail);

    }

    private Optional<Customer> existsCustomer(long idCustomer) {
        Optional<Customer> customerResult = customerRepository.findById(idCustomer);
        return customerResult;
    }

    private Optional<Seller> existsSeller(long idSeller) {
        Optional<Seller> sellerResult = sellerRepository.findById(idSeller);
        return sellerResult;
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
        Optional<Purchase> purchaseResult = purchaseRepository.findById(idPurchase);
        purchaseResult.get().setIdPurchase(idPurchase);
        purchaseResult.get().setUpdateDate(LocalDateTime.now());
        purchaseRepository.save(purchaseResult.get());

        PurchaseDetail purchaseDetail = PurchaseDetail.builder()
                .purchaseState(PurchaseState.APROBADA)
                .purchase(purchaseResult.get()).build();
        purchaseDetailRepository.save(purchaseDetail);
    }


}
