package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.ClientDTO;
import ar.com.plug.examen.app.api.OrderDTO;
import ar.com.plug.examen.app.api.OrderRequest;
import ar.com.plug.examen.app.api.SellerDTO;
import ar.com.plug.examen.domain.converter.ClientConverter;
import ar.com.plug.examen.domain.repository.OrderRepository;
import ar.com.plug.examen.domain.service.ClientService;
import ar.com.plug.examen.domain.service.OrderService;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.domain.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Implementation of {@link OrderService}
 */
@Service
public class OrderServiceImpl implements OrderService
{

    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ClientConverter clientConverter;

    @Override
    @Transactional
    public OrderDTO save(OrderRequest orderRequest)
    {
        ClientDTO clientById = clientService.getClientById(orderRequest.getClientId());
        SellerDTO sellerById = sellerService.getSellerById(orderRequest.getSellerId());

        orderRequest.getProductRequests().stream()
              .forEach(orderRequest1 -> {


              });

        return null;
    }
//    @Override
//    @Transactional
//    public TransactionApi save(TransactionApiRquest transactionApiRquest)
//          throws GenericBadRequestException {
//        this.validator.validateTransaction(transactionApiRquest);
//        Transaction transactionToSave = preparetoSave(transactionApiRquest);
//        return this.transactionMapper
//              .transactionToTransactionApi(this.transactionRepository.save(transactionToSave));
//    }
//
//    private Transaction preparetoSave(TransactionApiRquest transactionApiRquest) {
//        ClientApi client = this.clientService.findByIdChecked(
//              transactionApiRquest.getClientId());
//        SellerApi seller = this.sellerService.findByIdChecked(
//              transactionApiRquest.getSellerId());
//        List<Product> lsProducts = this.productService
//              .getProductsWithStock(transactionApiRquest.getLsProducts());
//        List<TransactionItems> lsItems = new ArrayList<>();
//        for (Product product : lsProducts) {
//            TransactionItems item = new TransactionItems();
//            ProductStockApi productStockApi = transactionApiRquest.getLsProducts().stream()
//                  .filter(i -> i.getIdProduct().equals(product.getId())).collect(
//                        Collectors.toList()).get(0);
//            item.setQuantity(productStockApi.getQuantity());
//            product.setStock(product.getStock() - productStockApi.getQuantity());
//            item.setProduct(product);
//            lsItems.add(item);
//        }
//        TransactionApi transactionApi = new TransactionApi(client, seller,
//              this.transactionItemMapper.transactionItemsToListTransactionitemsApi(lsItems), new Date(),
//              TransactionStatusEnum.PENDING);
//        Transaction transactionToSave = this.transactionMapper
//              .transactionApiToTransaction(transactionApi);
//        for (TransactionItems item : transactionToSave.getTransactionItems()) {
//            item.setTransaction(transactionToSave);
//        }
//        return transactionToSave;
//    }
//
//    /**
//     * (non-Javadoc)
//     *
//     * @see ar.com.plug.examen.domain.service.TransactionService#updateStatus(Long,
//     * TransactionStatusEnum)
//     */
//    @Override
//    @Transactional
//    public TransactionApi updateStatus(Long id, TransactionStatusEnum status)
//          throws GenericNotFoundException, GenericBadRequestException {
//        this.validator.validateTransactionStatus(status);
//        Transaction transactionToUpdate = this.transactionRepository.findById(id)
//              .orElseThrow(() -> new GenericNotFoundException("Transaction not found"));
//        transactionToUpdate.setStatus(status);
//        return this.transactionMapper
//              .transactionToTransactionApi(this.transactionRepository.save(transactionToUpdate));
//    }
}
