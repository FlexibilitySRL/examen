package ar.com.flexibility.examen.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.app.api.ClientApi;
import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.app.api.PurchasesApi;
import ar.com.flexibility.examen.app.api.SellerApi;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchases;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.service.ProcessPurchasesService;
import ar.com.flexibility.examen.repository.ClientRepository;
import ar.com.flexibility.examen.repository.ProductRepository;
import ar.com.flexibility.examen.repository.PurchasesRepository;
import ar.com.flexibility.examen.repository.SellerRepository;

@Service
public class ProcessPurchasesServiceImpl implements ProcessPurchasesService {

	private final Logger log = LoggerFactory.getLogger(ProcessPurchasesServiceImpl.class);

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	SellerRepository sellerRepository;

	@Autowired
	PurchasesRepository purchasesRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public PurchasesApi create(PurchasesApi purchasesApi) {
		Purchases purchases = modelMapper.map(purchasesApi, Purchases.class);
		Client client = clientRepository.findById(purchasesApi.getClientApi().getId());
		Product product = productRepository.findById(purchasesApi.getProductApi().getId());
		Seller seller = sellerRepository.findById(purchasesApi.getSellerApi().getId());
		purchases.setClient(client);
		purchases.setProduct(product);
		purchases.setSeller(seller);
		purchases = purchasesRepository.saveAndFlush(purchases);
		purchasesApi = modelMapper.map(purchases, PurchasesApi.class);
		log.info("Purchase successfully approved.");
		return purchasesApi;
	}

	@Override
	public List<PurchasesApi> search() {
		List<PurchasesApi> purchasesList = new ArrayList<PurchasesApi>();
		List<Purchases> purchases = purchasesRepository.findAll();
		purchases.forEach(purchase -> {
			PurchasesApi purchasesApi = modelMapper.map(purchase, PurchasesApi.class);
			purchasesApi.setClientApi(modelMapper.map(purchase.getClient(), ClientApi.class));
			purchasesApi.setProductApi(modelMapper.map(purchase.getProduct(), ProductApi.class));
			purchasesApi.setSellerApi(modelMapper.map(purchase.getSeller(), SellerApi.class));
			purchasesList.add(purchasesApi);
		});
		log.info("Purchases returned successfully.");
		return purchasesList;
	}

}
