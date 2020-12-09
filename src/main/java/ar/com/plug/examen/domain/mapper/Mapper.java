package ar.com.plug.examen.domain.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.api.PurchaseApprovalApi;
import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Transaction;

@Component
public class Mapper {
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static Product mapperToProduct(ProductApi productApi) {
		Product product = modelMapper.map(productApi, Product.class);
		return product;
	}
	
	public static ProductApi mapperToProductApi(Product product) {
		return modelMapper.map(product, ProductApi.class);
	}
	
	public static Client mapperToClient(ClientApi productApi) {
		return modelMapper.map(productApi, Client.class);
	}
	
	public static ClientApi mapperToClientApi(Client client) {
		return modelMapper.map(client, ClientApi.class);
	}

	public static Transaction mapperToTransaction(PurchaseApprovalApi purchaseApprovalApi) {
		List<Product> products = new ArrayList<Product>();
		Transaction transaction =  new Transaction();
		
		purchaseApprovalApi.getProduct().forEach(p -> products.add(mapperToProduct(p)));
		transaction.setProduct(products);
		transaction.setClient(mapperToClient(purchaseApprovalApi.getClient()));
		
		return transaction;
	}
	public static TransactionApi mapperToTransactionApi(Transaction transaction) {
		List<ProductApi> productsApi = new ArrayList<>();
		TransactionApi transactionApi =  new TransactionApi();
		
		transaction.getProduct().forEach(p -> productsApi.add(mapperToProductApi(p)));
		transactionApi.setProduct(productsApi);
		transactionApi.setClient(mapperToClientApi(transaction.getClient()));
		transactionApi.setDate(transaction.getDate());
		return transactionApi;
	}
	
}
