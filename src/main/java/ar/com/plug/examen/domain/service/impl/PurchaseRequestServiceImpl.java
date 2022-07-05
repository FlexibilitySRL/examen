package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.PurchaseDTO;
import ar.com.plug.examen.app.api.PurchaseProductDTO;
import ar.com.plug.examen.app.dtoResponse.ErrorDTO;
import ar.com.plug.examen.app.dtoResponse.ListPurchaseResponseDTO;
import ar.com.plug.examen.app.dtoResponse.PurchaseResponseDTO;
import ar.com.plug.examen.app.entity.ClientEntity;
import ar.com.plug.examen.app.entity.ProductEntity;
import ar.com.plug.examen.app.entity.PurchaseEntity;
import ar.com.plug.examen.app.entity.SellerEntity;
import ar.com.plug.examen.app.enumeration.ErrorCodeEnumeration;
import ar.com.plug.examen.app.enumeration.PurchaseStatusEnumeration;
import ar.com.plug.examen.app.exception.ApiException;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.repository.PurchaseRepository;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.service.IPurchaseRequestService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PurchaseRequestServiceImpl implements IPurchaseRequestService {

    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final SellerRepository sellerRepository;
    Log log = LogFactory.getLog(this.getClass());

    @Autowired
    public PurchaseRequestServiceImpl (final PurchaseRepository purchaseRepository, final ProductRepository productRepository,
                                       final ClientRepository clientRepository, final SellerRepository sellerRepository) {
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
        this.sellerRepository = sellerRepository;
    }

    @Transactional(readOnly = false)
    public PurchaseResponseDTO create(PurchaseDTO dto) throws IOException {
        ProductEntity product;
        int item = 1;
        DateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date();
        Long receipt = Long.parseLong(formatter.format(date));
        log.info("purchase - create - " + dto.toString());

        try {
            ClientEntity client = clientRepository.findByDocumentNumber(dto.getClientDocument());
            if (client == null) {
                log.error("purchase - create - CLIENT NOT EXIST");
                throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INVALID_CLIENT));
            }
            log.info("purchase - create - CLIENT EXIST - client_id: " + client.getClientId());
        } catch (Exception e) {
            log.error("purchase - create - ERROR: " + e.getMessage());
            throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INVALID_CLIENT));
        }

        try {
            SellerEntity seller = sellerRepository.findBySellerId(dto.getSellerId());
            if (seller == null) {
                log.error("purchase - create - SELLER NOT EXIST");
                throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INVALID_SELLER));
            }
            log.info("purchase - create - SELLER EXIST - seller_id: " + seller.getSellerId());
        } catch (Exception e) {
            log.error("purchase - create - ERROR: " + e.getMessage());
            throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INVALID_SELLER));
        }

        try {
            for (PurchaseProductDTO purchaseProductDTO : dto.getPurchaseProductsList() ) {
                product = productRepository.findByProductCode(purchaseProductDTO.getProductCode());
                log.info("purchase - create - PRODUCT EXIST - product_id: " + product.getProductId());

                if (product == null) {
                    log.error("purchase - create - PRODUCT NOT EXIST");
                    throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INVALID_PRODUCT));
                }

                PurchaseEntity newPurchase = PurchaseEntity.builder()
                        .receiptId(receipt)
                        .item(item++)
                        .clientDocument(dto.getClientDocument())
                        .productCode(purchaseProductDTO.getProductCode())
                        .purchaseQuantity(purchaseProductDTO.getPurchaseQuantity())
                        .productPrice(product.getProductPrice())
                        .sellerId(dto.getSellerId())
                        .purchaseDate(date)
                        .status(PurchaseStatusEnumeration.PENDING.toString())
                        .build();

                purchaseRepository.save(newPurchase);
                log.info("purchase - create - PURCHASE SAVED");
            }

            log.info("purchase - create - ALL PURCHASES SAVED");
            return PurchaseResponseDTO.builder()
                    .receiptId(receipt)
                    .status(HttpStatus.OK.toString())
                    .code("CREATED").build();

        } catch (Exception e) {
            log.error("purchase - create - ERROR: " + e.getMessage());
            throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INTERNAL_ERROR));
        }
    }

    @Transactional(readOnly = false)
    public PurchaseResponseDTO approve(Long receiptId) throws IOException {
        String approved = PurchaseStatusEnumeration.APPROVED.toString();
        String pending = PurchaseStatusEnumeration.PENDING.toString();
        log.info("purchase - approve - " + receiptId);

        try {
            List<PurchaseEntity> updatePurchases = purchaseRepository.findByReceiptId(receiptId);
            log.info("purchase - approve - PURCHASE LIST SIZE: " + updatePurchases.size());

            if (updatePurchases != null && !updatePurchases.isEmpty()) {
                if (!updatePurchases.get(0).getStatus().equalsIgnoreCase(pending)) {
                    log.error("purchase - approve - PURCHASE IS NOT IN PENDING STATUS");
                    throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INVALID_UPDATE));
                }

                for (PurchaseEntity purchaseEntity : updatePurchases ) {
                    purchaseEntity.setStatus(approved);
                    purchaseRepository.save(purchaseEntity);
                }
                log.info("purchase - approve - PURCHASE UPDATED");

                return PurchaseResponseDTO.builder()
                        .receiptId(receiptId)
                        .status(HttpStatus.OK.toString())
                        .code("UPDATED").build();
            }

            if (updatePurchases == null) {
                log.error("purchase - approve - RECEIPT ID NOT FOUND");
                throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INVALID_RECEIPT));
            }

            return PurchaseResponseDTO.builder()
                    .receiptId(receiptId != null ? receiptId : 0L)
                    .status(HttpStatus.BAD_REQUEST.toString())
                    .code("FAILED").build();

        } catch (Exception e) {
            log.error("purchase - approve - ERROR: " + e.getMessage());
            throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INTERNAL_ERROR));
        }
    }

    @Transactional(readOnly = false)
    public PurchaseResponseDTO cancel(Long receiptId) throws IOException {
        String canceled = PurchaseStatusEnumeration.CANCELED.toString();
        String pending = PurchaseStatusEnumeration.PENDING.toString();
        log.info("purchase - cancel - " + receiptId);

        try {
            List<PurchaseEntity> updatePurchases = purchaseRepository.findByReceiptId(receiptId);
            log.info("purchase - cancel - PURCHASE LIST SIZE: " + updatePurchases.size());

            if (updatePurchases != null && !updatePurchases.isEmpty()) {
                if (!updatePurchases.get(0).getStatus().equalsIgnoreCase(pending)) {
                    log.error("purchase - cancel - PURCHASE IS NOT IN PENDING STATUS");
                    throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INVALID_UPDATE));
                }

                for (PurchaseEntity purchaseEntity : updatePurchases ) {
                    purchaseEntity.setStatus(canceled);
                    purchaseRepository.save(purchaseEntity);
                }
                log.info("purchase - cancel - PURCHASE UPDATED");

                return PurchaseResponseDTO.builder()
                        .receiptId(receiptId)
                        .status(HttpStatus.OK.toString())
                        .code("UPDATED").build();
            }

            if (updatePurchases == null) {
                log.error("purchase - cancel - RECEIPT ID NOT FOUND");
                throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INVALID_RECEIPT));
            }

            return PurchaseResponseDTO.builder()
                    .receiptId(receiptId != null ? receiptId : 0L)
                    .status(HttpStatus.BAD_REQUEST.toString())
                    .code("FAILED").build();

        } catch (Exception e) {
            log.error("purchase - cancel - ERROR: " + e.getMessage());
            throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INTERNAL_ERROR));
        }
    }

    public ListPurchaseResponseDTO list() throws IOException {
        try {
            log.info("purchase - list");
            List<PurchaseEntity> listPurchase = purchaseRepository.findAll();
            ListPurchaseResponseDTO listPurchases = new ListPurchaseResponseDTO();
            listPurchases.setPurchases(new ArrayList<>());
            Long receiptId = 0L;
            List<PurchaseProductDTO> purchaseProductsList = new ArrayList<>();

            if (listPurchase != null) {
                log.info("purchase - list - LIST SIZE: " + listPurchase.size());
                for (PurchaseEntity purchaseEntity : listPurchase) {
                    log.info("purchase - list - ITEM: " + purchaseEntity.getItem());
                    if(!receiptId.equals(purchaseEntity.getReceiptId())) {
                        receiptId = purchaseEntity.getReceiptId();
                        purchaseProductsList = new ArrayList<>();

                        purchaseProductsList.add(new PurchaseProductDTO(
                                purchaseEntity.getItem(),
                                purchaseEntity.getProductCode(),
                                purchaseEntity.getPurchaseQuantity(),
                                purchaseEntity.getProductPrice()
                        ));

                        listPurchases.getPurchases().add(new Purchase(
                                purchaseEntity.getPurchaseId(),
                                purchaseEntity.getReceiptId(),
                                purchaseEntity.getClientDocument(),
                                purchaseProductsList,
                                purchaseEntity.getSellerId(),
                                purchaseEntity.getPurchaseDate(),
                                purchaseEntity.getStatus()
                        ));
                    } else {
                        purchaseProductsList.add(new PurchaseProductDTO(
                                purchaseEntity.getItem(),
                                purchaseEntity.getProductCode(),
                                purchaseEntity.getPurchaseQuantity(),
                                purchaseEntity.getProductPrice()
                        ));
                    }


                }
                return listPurchases;
            }
            log.error("purchase - list - LIST NOT EXIST");
            return null;
        } catch (Exception e) {
            log.error("purchase - list - ERROR: " + e.getMessage());
            throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INTERNAL_ERROR));
        }
    }
}
