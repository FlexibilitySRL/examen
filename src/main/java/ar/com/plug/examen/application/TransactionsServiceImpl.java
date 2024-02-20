package ar.com.plug.examen.application;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.ItemProduct;
import ar.com.plug.examen.domain.Transaction;
import ar.com.plug.examen.domain.service.TransactionsService;
import ar.com.plug.examen.infrastructure.db.entity.ClientEntity;
import ar.com.plug.examen.infrastructure.db.entity.ItemProductEntity;
import ar.com.plug.examen.infrastructure.db.entity.ProductEntity;
import ar.com.plug.examen.infrastructure.db.entity.TransactionEntity;
import ar.com.plug.examen.infrastructure.db.repository.ClientEntityRepository;
import ar.com.plug.examen.infrastructure.db.repository.ProductEntityRepository;
import ar.com.plug.examen.infrastructure.db.repository.TransactionEntityRepository;
import ar.com.plug.examen.infrastructure.rest.dto.ResponseDto;
import ar.com.plug.examen.shared.config.MenssageResponse;
import ar.com.plug.examen.shared.exception.ConflictException;
import ar.com.plug.examen.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionsServiceImpl implements TransactionsService {

    private final ClientEntityRepository clientEntityRepository;
    private final ProductEntityRepository productEntityRepository;
    private final TransactionEntityRepository transactionEntityRepository;
    private final MenssageResponse menssageResponse;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        log.info("Inicia creacion de transaccion :{}", transaction);
        ClientEntity clientEntity = this.getIfClientExist(transaction.getClientId());
        List<ItemProductEntity> itemProductEntities = this.buidlItemProductEntity(transaction.getItems());
        TransactionEntity transactionEntity = TransactionEntity.builder().approved(false).client(clientEntity)
                .total(this.getTotal(transaction.getItems()))
                .date(LocalDateTime.now())
                .items(itemProductEntities)
                .build();
        clientEntity.addTransactions(transactionEntity);
        clientEntityRepository.save(clientEntity);
        return transactionEntity.toTransaction();
    }

    @Override
    public List<Transaction> findByClientEmail(String email, Boolean approved) {
        ClientEntity clientEntity = clientEntityRepository.findByEmail(email).orElseThrow(() -> {
            log.error("No existe el cliente con email: {}", email);
            return new NotFoundException(ResponseDto.builder()
                    .code(MenssageResponse.C404)
                    .message(menssageResponse.getMessages().get(MenssageResponse.C404).concat(email))
                    .build());
        });
        return clientEntity.getTransactions().stream().map(TransactionEntity::toTransaction)
                .collect(Collectors.toList());

    }

    @Override
    public List<Transaction> findByApproved(Boolean approved) {
        return transactionEntityRepository.findByApproved(approved).stream().map(TransactionEntity::toTransaction)
                .collect(Collectors.toList());

    }

    @Override
    public List<Transaction> findByDate(LocalDateTime fromDate, LocalDateTime toDate, Boolean approved) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByDate'");
    }

    @Override
    public Map<String, String> approvedTransacctions(List<String> ids) {
        Map<String, String> response = new HashMap<>();
        List<TransactionEntity> transactionEntities = (List<TransactionEntity>) transactionEntityRepository
                .findAllById(ids);
        transactionEntities.forEach(tansacction -> tansacction.setApproved(true));
        transactionEntityRepository.saveAll(transactionEntities);
        ids.forEach(id -> {
            response.put(id, transactionEntities.stream().anyMatch(transacction -> transacction.getId().equals(id))
                    ? "Approved"
                    : "Not Found");
        });
        return response;

    }

    private ClientEntity getIfClientExist(String id) {
        return clientEntityRepository.findById(id).orElseThrow(() -> {
            log.error("No existe el cliente con id: {}", id);
            return new NotFoundException(ResponseDto.builder()
                    .code(MenssageResponse.C403)
                    .message(menssageResponse.getMessages().get(MenssageResponse.C403).concat(id))
                    .build());
        });
    }

    private List<ItemProductEntity> buidlItemProductEntity(List<ItemProduct> items) {
        List<ItemProductEntity> itemProductEntities = new ArrayList<>();
        List<ProductEntity> productEntities = items.stream().map(item -> {
            ProductEntity productEntity = productEntityRepository.findById(item.getProductId()).orElseThrow(() -> {
                log.error("No existe el producto con id: {}", item.getProductId());
                return new NotFoundException(ResponseDto.builder()
                        .code(MenssageResponse.P404)
                        .message(menssageResponse.getMessages().get(MenssageResponse.P404).concat(item.getProductId()))
                        .build());
            });
            if (productEntity.getInventory() < item.getQuantity()) {
                throw new ConflictException(ResponseDto.builder()
                        .code(MenssageResponse.T401)
                        .message(menssageResponse.getMessages().get(MenssageResponse.T401).concat(item.getProductId()))
                        .build());
            }
            productEntity.setInventory(productEntity.getInventory() - item.getQuantity());

            itemProductEntities.add(new ItemProductEntity(null, productEntity, item.getQuantity(), item.getPrice()));
            return productEntity;
        }).collect(Collectors.toList());
        productEntityRepository.saveAll(productEntities);

        return itemProductEntities;
    }

    private Double getTotal(List<ItemProduct> items) {
        return items.stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();
    }

}
