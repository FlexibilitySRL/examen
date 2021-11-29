package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.dto.ClientDto;
import ar.com.plug.examen.app.dto.ProductStockDto;
import ar.com.plug.examen.app.dto.SellerDto;
import ar.com.plug.examen.app.dto.TransactionDto;
import ar.com.plug.examen.app.dto.TransactionApiRequest;
import ar.com.plug.examen.domain.enums.TransactionStatusEnum;
import ar.com.plug.examen.domain.exceptions.GenericBadRequestException;
import ar.com.plug.examen.domain.exceptions.GenericNotFoundException;
import ar.com.plug.examen.domain.mappers.TransactionItemsMapper;
import ar.com.plug.examen.domain.mappers.TransactionMapper;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.model.TransactionItems;
import ar.com.plug.examen.domain.repository.TransactionRepository;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.domain.service.SellerService;
import ar.com.plug.examen.domain.service.TransactionService;
import ar.com.plug.examen.domain.validators.Validator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private TransactionItemsMapper transactionItemMapper;

    @Autowired
    private Validator validator;

    @Autowired
    private ClientService clientService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ProductService productService;

    /**
     * (non-Javadoc)
     *
     * @see ar.com.plug.examen.domain.service.TransactionService#findAll()
     */
    @Override
    public List<TransactionDto> findAll() {
        return this.transactionMapper
                .transactionsToListTransactionApi(this.transactionRepository.findAll());
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * ar.com.plug.examen.domain.service.TransactionService#findByIdChecked(Long)
     */
    @Override
    public TransactionDto findByIdChecked(Long id) throws GenericNotFoundException {
        return this.transactionMapper.transactionToTransactionApi(
                this.transactionRepository.findById(id)
                        .orElseThrow(() -> new GenericNotFoundException("Transaction not found")));
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * ar.com.plug.examen.domain.service.TransactionService#save(TransactionApiRquest)
     */
    @Override
    @Transactional
    public TransactionDto save(TransactionApiRequest transactionApiRquest)
            throws GenericBadRequestException {
        this.validator.validateTransaction(transactionApiRquest);
        Transaction transactionToSave = preparetoSave(transactionApiRquest);
        return this.transactionMapper
                .transactionToTransactionApi(this.transactionRepository.save(transactionToSave));
    }

    private Transaction preparetoSave(TransactionApiRequest transactionApiRquest) {
        ClientDto client = this.clientService.findById(
                transactionApiRquest.getClientId());
        SellerDto seller = this.sellerService.findByIdChecked(
                transactionApiRquest.getSellerId());
        List<Product> lsProducts = this.productService
                .getProductsWithStock(transactionApiRquest.getListProducts());
        List<TransactionItems> lsItems = new ArrayList<>();
        for (Product product : lsProducts) {
            TransactionItems item = new TransactionItems();
            ProductStockDto productStockApi = transactionApiRquest.getListProducts().stream()
                    .filter(i -> i.getIdProduct().equals(product.getId())).collect(
                    Collectors.toList()).get(0);
            item.setQuantity(productStockApi.getQuantity());
            product.setStock(product.getStock() - productStockApi.getQuantity());
            item.setProduct(product);
            lsItems.add(item);
        }
        TransactionDto transactionApi = new TransactionDto(client, seller,
                this.transactionItemMapper.transactionItemsToListTransactionitemsApi(lsItems), new Date(),
                TransactionStatusEnum.PENDING);
        Transaction transactionToSave = this.transactionMapper
                .transactionApiToTransaction(transactionApi);
        for (TransactionItems item : transactionToSave.getTransactionItems()) {
            item.setTransaction(transactionToSave);
        }
        return transactionToSave;
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * ar.com.plug.examen.domain.service.TransactionService#updateStatus(Long,
     * TransactionStatusEnum)
     */
    @Override
    @Transactional
    public TransactionDto updateStatus(Long id, TransactionStatusEnum status)
            throws GenericNotFoundException, GenericBadRequestException {
        this.validator.validateTransactionStatus(status);
        Transaction transactionToUpdate = this.transactionRepository.findById(id)
                .orElseThrow(() -> new GenericNotFoundException("Transaction not found"));
        transactionToUpdate.setStatus(status);
        return this.transactionMapper
                .transactionToTransactionApi(this.transactionRepository.save(transactionToUpdate));
    }
}
