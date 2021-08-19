package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.Exception.PaymentException;
import ar.com.plug.examen.Exception.SaleException;
import ar.com.plug.examen.domain.converter.CustomerConverter;
import ar.com.plug.examen.domain.converter.PaymentTypeConverter;
import ar.com.plug.examen.domain.converter.SaleConverter;
import ar.com.plug.examen.domain.converter.SellerConverter;
import ar.com.plug.examen.domain.dto.ItemByLine;
import ar.com.plug.examen.domain.dto.Product;
import ar.com.plug.examen.domain.dto.Sale;
import ar.com.plug.examen.domain.model.SaleModel;
import ar.com.plug.examen.domain.repository.ItemLineRepository;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.repository.SaleRepository;
import ar.com.plug.examen.domain.service.SaleService;
import ar.com.plug.examen.objects.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static ar.com.plug.examen.utils.ConstantUtil.*;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class SaleServiceImpl implements SaleService {
    private static final Log log = LogFactory.getLog(SaleServiceImpl.class);

    private final ObjectMapper objectMapper;

    @Autowired
    private final SaleConverter saleConverter;

    @Autowired
    private final SaleRepository saleRepository;

    @Autowired
    private final CustomerConverter customerConverter;

    @Autowired
    private final SellerConverter sellerConverter;

    @Autowired
    private final PaymentTypeConverter paymentConverter;

    private final ProductRepository productRepository;

    @Autowired
    private final ItemLineRepository itemLineRepository;

    private static final String LOG_CONSTANT="Call: sale ";

    @Override
    public JsonResponseTransaction createSale(JsonRequestSale jsonRequestSale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        JsonResponseTransaction jsonResponseTransaction = new JsonResponseTransaction();
        SaleModel saleModel = new SaleModel();
        saleModel.setDateTrx((LocalDate.now().format(formatter)));
        saleModel.setCustomer(customerConverter.convertFromEntity(jsonRequestSale.getCustomerModel()));
        saleModel.setSeller(sellerConverter.convertFromEntity(jsonRequestSale.getSellerModel()));
        //Status pending until finish sale
        saleModel.setIdStatus(3);
        jsonResponseTransaction.setSaleModel(this.addSale(saleModel));
        log.info(jsonResponseTransaction.getSaleModel().toString());
        return jsonResponseTransaction;
    }

    @Override
    public SaleModel addSale(SaleModel saleModel) {
        log.info(LOG_CONSTANT + " addSaleModel");
        System.out.println("Dato " + saleModel.getDateTrx().toString());
        Sale sale = saleRepository.save(saleConverter.convertFromEntity(saleModel));
        log.info("create sale: "+saleModel.toString());
        return saleConverter.convertFromModel(sale);
    }

    @Override
    public StatusTransaction deleteSale(Long id) {
        log.info(LOG_CONSTANT + " deleteSaleModel "+id);
        saleRepository.deleteById(id);
        log.info("delete sale: "+StatusTransaction.DELETE);
        return StatusTransaction.DELETE;
    }

    @Override
    public SaleModel updateSale(SaleModel saleModel) {
        log.info(LOG_CONSTANT + " updateSaleModel before: "+saleRepository.findById(saleModel.getId()) + "after: "+saleModel.toString());
        Sale sale = saleRepository.save(saleConverter.convertFromEntity(saleModel));
        return saleConverter.convertFromModel(sale);
    }

    @Override
    public List<SaleModel> getSaleTrxAll() {
        log.info(LOG_CONSTANT + "Query sale transactions");
        List<Sale> getSaleTrxAll= saleRepository.findAll();
        return saleConverter.createFromDtos(getSaleTrxAll);
    }

    @Override
    public List<Product> getProductsAvailableForSale(JsonRequestSale jsonRequestSale) {
        log.info(LOG_CONSTANT + " getProductsAvailableForSale");
        Set<RequestCustomer> productMapRequest= jsonRequestSale.getProductMapRequest();
        List<Product> availableProducts = new ArrayList<>();
        productMapRequest.stream().forEach(requestCustomer -> {
                Product productRequest=productRepository.findById(requestCustomer.getProduct().getId());
                    if (productRequest.getQuantity()>=requestCustomer.getQuantityRequired() && productRequest.getIdStatus()==1) {
                        availableProducts.add(productRepository.findById(requestCustomer.getProduct().getId()));
                    } else {
                        log.info("Product no available for the sell. Stock sold out or status product incorrect");
                    }
                });
        return availableProducts;
    }

    @SneakyThrows(JsonProcessingException.class)
    @Override
    public JsonResponseTransaction confirmSale(JsonRequestSale jsonRequestSale) {
        log.info(LOG_CONSTANT + " confirmSale");
        JsonResponseTransaction jsonResponseTransaction = new JsonResponseTransaction();
        SaleModel saleModel= addItemsToTrx(jsonRequestSale);
        jsonRequestSale.setTotalRequiredByPayment(saleModel.getTotal());
        boolean confirmSale = this.setPaymentType(jsonRequestSale);
        jsonResponseTransaction.setConfirmSale(confirmSale);
        saleModel.setPaymentType(paymentConverter.convertFromEntity(jsonRequestSale.getPaymentType()));

        if(confirmSale){
            log.info("Process confirm sale with successfully");
            saleModel.setIdStatus(1);
            saleModel.getItems().stream().forEach(items -> {
                Integer updateInventory= items.getProduct().getQuantity()-items.getQuantity();
                items.getProduct().setQuantity(updateInventory);
                Product updateProduct = productRepository.save(items.getProduct());
                log.info("Update inventory by product: "+updateProduct.getId());
            });
        }else{
            log.info("Disable sale, change of sale status inactive.");
            saleModel.setIdStatus(2);
        }
        jsonResponseTransaction.setJsonMessage(createJsonMessage(saleModel));
        this.updateSale(saleModel);
        jsonResponseTransaction.setSaleModel(objectMapper.readValue(jsonResponseTransaction.getJsonMessage(), SaleModel.class));

        return jsonResponseTransaction;
    }

    private String createJsonMessage(SaleModel saleModel) throws JsonProcessingException {
        return objectMapper.writeValueAsString(saleModel);
    }

    private SaleModel getCurrentSaleModel(){
        if(saleRepository.returnCount()<1){
            throw new PaymentException(CREATE_SALE_ERROR);
        }
        return saleConverter.convertFromModel(saleRepository.findById(saleRepository.findTopByOrderByIdDesc()));
    }

    public SaleModel addItemsToTrx(JsonRequestSale jsonRequestSale){
        SaleModel saleModel = this.getCurrentSaleModel();
        List<Product> availableProducts= this.getProductsAvailableForSale(jsonRequestSale);
        Set<RequestCustomer> productMapRequest= jsonRequestSale.getProductMapRequest();

        List<ItemByLine> itemByLines = new ArrayList<>();
        Double total = 0.0;
        Double tax=0.0;
        Double subTotal=0.0;

        if(productMapRequest.isEmpty() && availableProducts.isEmpty()){
            saleModel.setIdStatus(2);
            throw new SaleException(DISABLE_PRODUCTS);
        }

        for(RequestCustomer requestCustomer:productMapRequest){
            for(Product product: availableProducts){
                if(requestCustomer.getProduct().getId().equals(product.getId())){
                    Double totalItem=0.0;
                    ItemByLine itemByLine = new ItemByLine();
                    itemByLine.setProduct(product);
                    itemByLine.setQuantity(requestCustomer.getQuantityRequired());
                    totalItem=requestCustomer.getQuantityRequired()*product.getSalePrice();
                    itemByLine.setTotalAmountProduct(totalItem);
                    total+=totalItem;
                    itemByLine.setIdStatus(product.getIdStatus());
                    itemByLine.setSale(saleConverter.convertFromEntity(saleModel));
                    itemLineRepository.save(itemByLine);
                    itemByLines.add(itemByLine);
                }
            }
        }

        saleModel.setTotal(total);
        saleModel.setTax(total*Double.parseDouble(TAX));
        saleModel.setSubTotal(total - saleModel.getTax());
        saleModel.setItems(itemByLines);
        return saleModel;
    }

    @Override
    public boolean setPaymentType(JsonRequestSale jsonRequestSale) {
        if(nonNull(jsonRequestSale.getPaymentType()) && jsonRequestSale.getPaymentType().getIdStatus()==1){
            if(jsonRequestSale.getPaymentType().getAvailableBalance()>=jsonRequestSale.getTotalRequiredByPayment()){
                log.info("Payment mean available");
                return true;
            }
            else{
                log.error(INSUFFICIENT_BALANCE + "sale not successfully");
            }
        }
        return false;
    }
}
