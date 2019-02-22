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
import ar.com.flexibility.examen.domain.repository.ClientRepository;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.repository.PurchaseRepository;
import ar.com.flexibility.examen.domain.service.ClientService;

@Service
@Transactional(readOnly=true)
public class ClientServiceImpl implements ClientService {

		@Autowired
		private ClientRepository clientRepository;
		
		@Autowired
		private PurchaseRepository purchaseRepository;
		
		@Autowired
		private ProductRepository productRepository;

		public ClientServiceImpl(ClientRepository clientRepository) {
			this.clientRepository = clientRepository;
		}
	   
		@Transactional
		@Override
		public ClientApi saveOrUpdate(Client client) {
			return new ClientApi(this.clientRepository.save(client));
			
		}
		
		@Transactional
		@Override
		public List<PurchaseApi> findAllPurchaseOfClient(Long idClient) throws ClientNotFoundException {
			return getPurchaseApiList(purchaseRepository.findByClient(getClient(idClient)));
		}
		
		private List<PurchaseApi> getPurchaseApiList(List<Purchase> purchases) {
			return purchases.stream().map(p -> new PurchaseApi(p)).collect(Collectors.toList());
		}

		@Transactional
		@Override
		public void processPurchase(Long idClient, List<OrderApi> orders) throws ProductNotFoundException, ProductWithoutStock, InsufficientBalanceException, EmptyOrderException, ClientNotFoundException {
			Client client = getClient(idClient);
			if(orders != null && !orders.isEmpty()) {
				Double total = calculateTotal(orders);
				if(isOrderHasStock(orders) && client.isBalance(total)) {
					chargeClient(client,total);
					createPurchase(client, orders, total);
				}	
			}else {
				throw new EmptyOrderException();
			}
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
				Purchase purchase = new Purchase(client,new Date(),total);
				Order order = new Order(product,orderApi.getItems(),purchase);
				purchase.addOrder(order);
				purchaseRepository.save(purchase);
			}
			
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
			return product.isStock(orderApi.getItems());
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
				throw new ProductNotFoundException();
			}
			return product;
		}

		@Transactional
		@Override
		public void updateBalance(Long idClient, double amount) throws NegativeAmountException, ClientNotFoundException {
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
		
		public Client getClient(Long idClient) throws ClientNotFoundException {
			Client client = this.clientRepository.findOne(idClient);
			if(client == null) {
				throw new ClientNotFoundException();
		    }
			return client;
		}

		@Override
		public ClientApi getClientApi(Long idClient) throws ClientNotFoundException {
			return new ClientApi(this.getClient(idClient));
		}

		@Override
		public List<ProductApi> findAllProducts() {
			return getProductApiList(productRepository.findAll());
		}
		
		public List<ProductApi> getProductApiList(List<Product> productList) {
	     	return productList.stream().map(p -> new ProductApi(p)).collect(Collectors.toList());
		}
			
		
		
		

}
