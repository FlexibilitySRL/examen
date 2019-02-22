package ar.com.flexibility.examen.domain.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.api.OrderApi;
import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.app.api.PurchaseApi;
import ar.com.flexibility.examen.domain.exception.ClientNotFoundException;
import ar.com.flexibility.examen.domain.exception.EmptyOrderException;
import ar.com.flexibility.examen.domain.exception.InsufficientBalanceException;
import ar.com.flexibility.examen.domain.exception.NegativeAmountException;
import ar.com.flexibility.examen.domain.exception.ProductNotFoundException;
import ar.com.flexibility.examen.domain.exception.ProductWithoutStock;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Order;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.repository.SellerRepository;
import ar.com.flexibility.examen.domain.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional(readOnly=true)
public class ClientServiceImpl implements ClientService {
	
	   private final Logger log = LoggerFactory.getLogger(this.getClass());

		@Autowired
		private ClientRepository clientRepository;
		@Autowired
		private ProductRepository productRepository;
		
		@Autowired
		private SellerRepository sellerRepository;
	   
	   
		public ClientApi saveOrUpdate(Client client) {
			return new ClientApi(this.clientRepository.save(client));
			
		}
		
		@Transactional
		@Override
		public List<PurchaseApi> findAllPurchaseOfClient(Long idClient) throws ClientNotFoundException {
			log.debug("Getting all the purchase of the client:{}", idClient);
			return getPurchaseApiList(getClient(idClient).getPurchaseList());
		}
		
		private List<PurchaseApi> getPurchaseApiList(List<Purchase> purchases) {
			return purchases.stream().map(p -> new PurchaseApi(p)).collect(Collectors.toList());
		}

		@Transactional
		@Override
		public void processPurchase(Long idClient, List<OrderApi> orders) throws ProductNotFoundException, ProductWithoutStock, InsufficientBalanceException, EmptyOrderException, ClientNotFoundException {
			log.debug("Getting all the purchase of the client: {}", idClient);
			Client client = getClient(idClient);
			if(orders != null && !orders.isEmpty()) {
				Double total = calculateTotal(orders);
				if(isOrderHasStock(orders) && isClientWithBalance(client,total)) {
					chargeClient(client,total);
					createPurchase(client, orders, total);
				}	
			}else {
				log.error("No orders were found for the client: {}", idClient);
				throw new EmptyOrderException();
			}
		}
		
		private boolean isClientWithBalance(Client client,Double total) throws InsufficientBalanceException {
			if(client.getBalance() < total) {
				log.error("The client {} has insufficient balance. The Total of purchase is: {}", client.getIdClient(), total);
				throw new InsufficientBalanceException();
			}
			return true;
		}
		
		private void chargeClient(Client client,Double total) {
			client.discountBalance(total);
			clientRepository.save(client);
			
		}

		private void createPurchase(Client client, List<OrderApi> orders, Double total) throws ProductNotFoundException {
			for(OrderApi orderApi : orders) {
				Product product = getProductOfOrder(orderApi);
				product.discountStock(orderApi.getItems());
				productRepository.save(product);
				paySeller(product, orderApi.getItems());
				Purchase purchase = new Purchase(client,new Date(),total);
				Order order = new Order(product,orderApi.getItems(),purchase);
				purchase.addOrder(order);
				client.addPurchase(purchase);
				saveOrUpdate(client);
			}
			
		}

		private void paySeller(Product product, int items) {
			Seller seller = sellerRepository.findOne(product.getSeller().getIdSeller());
			Double totalToPay = product.getPrice() * items;
			seller.updateBalance(totalToPay);
			sellerRepository.save(seller);
			
		}

		private boolean isOrderHasStock(List<OrderApi> orders) throws ProductNotFoundException, ProductWithoutStock {
			boolean hasStock =  true;
			for(OrderApi orderApi : orders) {
				hasStock = hasStock && isProductWithStock(orderApi);
			}
			return hasStock;
		}

		private boolean isProductWithStock(OrderApi orderApi) throws ProductNotFoundException, ProductWithoutStock {
			Product product = getProductOfOrder(orderApi);
			 if(product.getStock() < orderApi.getItems()) {
				 log.error("The product with id: {} has not stock.", orderApi.getIdProduct());
				 throw new ProductWithoutStock();
			 }
			return true;
		}

		private Double calculateTotal(List<OrderApi> orders) throws ProductNotFoundException {
			Double total = 0D;
			for(OrderApi orderApi : orders) {
				total = total + calculateTotalProduct(orderApi);
			}
			return total;
		}
		
		private Double calculateTotalProduct(OrderApi orderApi) throws ProductNotFoundException {
			Product product = getProductOfOrder(orderApi);
			return product.getPrice() * orderApi.getItems();
		}

		private Product getProductOfOrder(OrderApi orderApi) throws ProductNotFoundException {
			Product product = productRepository.findOne(orderApi.getIdProduct());
			if(product == null) {
				log.error("The product with id: {} not was found", orderApi.getIdProduct());
				throw new ProductNotFoundException();
			}
			return product;
		}

		@Transactional
		@Override
		public void updateBalance(Long idClient, double amount) throws NegativeAmountException, ClientNotFoundException {
		    log.debug("Update balance of the client: {}. The amount received: {}", idClient, amount);
			Client client = getClient(idClient);
			validateAmount(amount);
			client.setBalance(client.getBalance() + amount);
			clientRepository.save(client);
		}
		
		private void validateAmount(double amount) throws NegativeAmountException {
			if(amount <= 0) {
				throw new NegativeAmountException();
			}
			
		}
		
		private Client getClient(Long idClient) throws ClientNotFoundException {
			Client client = this.clientRepository.findOne(idClient);
			if(client == null) {
				log.error("Client with id: {} not found", idClient);
				throw new ClientNotFoundException();
		    }
			return client;
		}

		@Override
		public ClientApi getClientApi(Long idClient) throws ClientNotFoundException {
			log.debug("Getting data of client {}", idClient);
			return new ClientApi(this.getClient(idClient));
		}

		@Override
		public List<ProductApi> findAllProducts() {
			log.debug("Getting all the products");
			return getProductApiList(productRepository.findAll());
		}
		
		private List<ProductApi> getProductApiList(List<Product> productList) {
	     	return productList.stream().map(p -> new ProductApi(p)).collect(Collectors.toList());
		}
}
