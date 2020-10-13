package ar.com.plug.examen.domain.utils;

import java.util.ArrayList;
import java.util.List;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.api.PurchaseDetailApi;
import ar.com.plug.examen.app.api.PurchaseDetailItemApi;
import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.PurchaseItem;
import ar.com.plug.examen.domain.model.Seller;

public class PaymentsSubsystemTranslator {
	
	private static PaymentsSubsystemTranslator instance;
	
	public static PaymentsSubsystemTranslator getInstance() {
		if (instance == null) {
			instance = new PaymentsSubsystemTranslator();
		}
		return instance;
	}
	
	//BEGIN CLIENT//
	public ClientApi getClientApi(Client client) {
		ClientApi clientApi = new ClientApi();
		this.fillApi(clientApi, client);
		return clientApi;
	}

	private void fillApi(ClientApi clientApi, Client client) {
		clientApi.setId(client.getId());
		clientApi.setName(client.getName());
		clientApi.setSurname(client.getSurname());
		clientApi.setEmail(client.getEmail());
		client.getPurchaseList().forEach((p) -> {
			clientApi.addPurchase(this.getPurchaseApi(p));
		});
	}
	
	public Client getClient(ClientApi clientApi) {
		Client client = new Client();
		this.fillEntity(client, clientApi);
		return client;
	}

	private void fillEntity(Client client, ClientApi clientApi) {
		client.setId(clientApi.getId());
		client.setName(clientApi.getName());
		client.setSurname(clientApi.getSurname());
		client.setEmail(clientApi.getEmail());
	}
	
	public List<ClientApi> getClientApiList(List<Client> clientList) {
		ArrayList<ClientApi> clientApiList = new ArrayList<ClientApi>();
		clientList.forEach((c) -> {
			clientApiList.add(this.getClientApi(c));
		});
		return clientApiList;
	}
	//END CLIENT//
	
	
	//BEGIN PRODUCT//
	public ProductApi getProductApi(Product product) {
		ProductApi productApi = new ProductApi();
		this.fillApi(productApi, product);
		return productApi;
	}
	
	private void fillApi(ProductApi productApi, Product product) {
		productApi.setId(product.getId());
		productApi.setName(product.getName());
		productApi.setPrice(product.getPrice());
	}
	
	public Product getProduct(ProductApi productApi) {
		Product product = new Product();
		this.fillEntity(product, productApi);
		return product;
	}

	private void fillEntity(Product product, ProductApi productApi) {
		product.setId(productApi.getId());
		product.setName(productApi.getName());
		product.setPrice(productApi.getPrice());
	}
	//END PRODUCT//
	
	//BEGIN PURCHASE//
	public PurchaseDetailApi getPurchaseApi(Purchase purchase) {
		PurchaseDetailApi purchaseApi = new PurchaseDetailApi();
		this.fillApi(purchaseApi, purchase);
		return purchaseApi;
	}
	
	private void fillApi(PurchaseDetailApi purchaseApi, Purchase purchase) {
		purchaseApi.setId(purchase.getId());
		purchaseApi.setDescription(purchase.getDescription());
		purchaseApi.setStatus(purchase.getStatus());
		purchaseApi.setSellerName(purchase.getSeller().getName());
		purchaseApi.setBuyerName(purchase.getClient().getName());
		purchase.getItems().forEach((i) -> {
			purchaseApi.addItem(this.getPurchaseItemApi(i));
		});
	}
	
	public List<PurchaseDetailApi> getPurchaseApiList(List<Purchase> purchases) {
		ArrayList<PurchaseDetailApi> purchaseApiList = new ArrayList<PurchaseDetailApi>();
		purchases.forEach((p) -> {
			purchaseApiList.add(this.getPurchaseApi(p));
		});
		return purchaseApiList;
	}
	
	//END PURCHASE//
	
	//BEGIN PURCHASE ITEM//
	public PurchaseDetailItemApi getPurchaseItemApi(PurchaseItem purchaseItem) {
		PurchaseDetailItemApi purchaseItemApi = new PurchaseDetailItemApi();
		this.fillApi(purchaseItemApi, purchaseItem);
		return purchaseItemApi;
	}
	
	private void fillApi(PurchaseDetailItemApi purchaseItemApi, PurchaseItem purchaseItem) {
		purchaseItemApi.setProduct(this.getProductApi(purchaseItem.getProduct()));
		purchaseItemApi.setQuantity(purchaseItem.getQuantity());
	}
	
	public PurchaseItem getPurchaseItem(PurchaseDetailItemApi purchaseItemApi) {
		PurchaseItem purchaseItem = new PurchaseItem();
		this.fillEntity(purchaseItem, purchaseItemApi);
		return purchaseItem;
	}

	private void fillEntity(PurchaseItem purchaseItem, PurchaseDetailItemApi purchaseItemApi) {
		purchaseItem.setProduct(this.getProduct(purchaseItemApi.getProduct()));
		purchaseItem.setQuantity(purchaseItemApi.getQuantity());
	}
	//END PURCHASE ITEM//

	
	//BEGIN SELLER//
	public SellerApi getSellerApi(Seller seller) {
		SellerApi sellerApi = new SellerApi();
		this.fillApi(sellerApi, seller);
		return sellerApi;
	}

	private void fillApi(SellerApi sellerApi, Seller seller) {
		sellerApi.setId(seller.getId());
		sellerApi.setName(seller.getName());
		sellerApi.setSurname(seller.getSurname());
		sellerApi.setEmail(seller.getEmail());
		if (seller.getSellList() != null) {
			seller.getSellList().forEach((p) -> {
				sellerApi.addSell(this.getPurchaseApi(p));
			});
		}
	}
	
	public Seller getSeller(SellerApi clientApi) {
		Seller client = new Seller();
		this.fillEntity(client, clientApi);
		return client;
	}

	private void fillEntity(Seller seller, SellerApi sellerApi) {
		seller.setName(sellerApi.getName());
		seller.setSurname(sellerApi.getSurname());
		seller.setEmail(sellerApi.getEmail());
	}	
	
	public List<SellerApi> getSellerApiList(List<Seller> sellerList) {
		ArrayList<SellerApi> sellerApiList = new ArrayList<SellerApi>();
		sellerList.forEach((c) -> {
			sellerApiList.add(this.getSellerApi(c));
		});
		return sellerApiList;
	}
	//END SELLER//


}
