package ar.com.flexibility.examen.domain.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import ar.com.flexibility.examen.domain.dto.ProductsRequestDTO;
import ar.com.flexibility.examen.domain.dto.PurchaseRequestDTO;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Purchases;
import ar.com.flexibility.examen.domain.repository.CustomerRepository;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.repository.PurchasesRepository;
import ar.com.flexibility.examen.domain.service.PurchasesService;

@Service
public class PurchasesServiceImpl implements PurchasesService {

	@Autowired
	private PurchasesRepository purchasesRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Boolean buy(PurchaseRequestDTO purchase) throws Exception {
		Customer customer = customerRepository.findOne(purchase.getIdCustomer());
		if (!CollectionUtils.isEmpty(purchase.getProducts()) && Objects.nonNull(customer)) {
			return buyProcess(purchase, customer);
		}
		return null;
	}

	synchronized private Boolean buyProcess(PurchaseRequestDTO purchase, Customer customer) throws Exception {
		Map<Long, Product> products = productRepository.findByIdIn(getListIdProducts(purchase)).stream()
				.collect(Collectors.toMap(Product::getId, prod -> prod));

		Stream<ProductsRequestDTO> streamProductwitoutStock = purchase.getProducts().stream()
				.filter(prod -> products.get(prod.getIdProduct()).getStock() < prod.getCount());
		if (streamProductwitoutStock.count() > 0) {
			throw new Exception("Hay productos sin stock"); // podria retornar una lista con los productos q no tienen
															// stock
		}
		List<Purchases> purchases = purchase.getProducts().stream()
				.map(buy -> this.buildPurchases(customer, products, buy)).collect(Collectors.toList());
		
		purchasesRepository.save(purchases);
		
		return Boolean.TRUE;
	}

	private List<Long> getListIdProducts(PurchaseRequestDTO purchase) {
		return purchase.getProducts().stream().map(buy -> buy.getIdProduct()).collect(Collectors.toList());
	}

	private Purchases buildPurchases(Customer customer, Map<Long, Product> products, ProductsRequestDTO buy) {
		Purchases purchases = new Purchases();
		Product product = products.get(buy.getIdProduct());
		product.setStock(product.getStock() - buy.getCount());
		purchases.setProduct(product);
		purchases.setPrice(product.getPrice());
		purchases.setCustomer(customer);
		purchases.setCreate(new Date());
		return purchases;
	}
}
