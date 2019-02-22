/**
 * 
 */
package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.exception.ProductNameNotAcceptedException;
import ar.com.flexibility.examen.domain.exception.ProductNotFoundException;
import ar.com.flexibility.examen.domain.exception.ProductPriceNotAcceptedException;
import ar.com.flexibility.examen.domain.exception.ProductStockNotAcceptedException;
import ar.com.flexibility.examen.domain.exception.SellerNotFoundException;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.SellerRepository;
import ar.com.flexibility.examen.domain.service.SellerService;
import ar.com.flexibility.examen.util.ValidationHelper;

/**
 * @author rosalizaracho
 *
 */
@Service
@Transactional(readOnly=true)
public class SellerServiceImpl implements SellerService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SellerRepository sellerRepository;
	
	private void saveOrUpdate(Seller seller) {
		log.debug("Saving seller");
		sellerRepository.save(seller);
		
	}
	
	@Transactional
	@Override
	public List<ProductApi>getProductsOfSeller(Long idSeller) throws SellerNotFoundException {
		log.debug("Getting all products of seller: {} ", idSeller);
		return getProductApiList(getSeller(idSeller).getProductList());
	}
	
	private Seller getSeller(Long idSeller) throws SellerNotFoundException {
		Seller seller = sellerRepository.findOne(idSeller);
    	if(seller == null) {
    		log.debug("No seller was found with the id: {} ", idSeller);
    		throw new SellerNotFoundException();
    	}
    	return seller;
	}

	public List<ProductApi> getProductApiList(List<Product> productList) {
     	return productList.stream().map(p -> new ProductApi(p)).collect(Collectors.toList());
	}
	
    @Transactional
	@Override
	public void createNewProductToSeller(ProductApi productApi) throws SellerNotFoundException, ProductNameNotAcceptedException, ProductPriceNotAcceptedException, ProductStockNotAcceptedException {
    	log.debug("Creating a new product to seller: {} ", productApi.getIdSeller());
    	Seller seller = getSeller(productApi.getIdSeller());
		Product product = new Product(productApi.getName(),productApi.getPrice(),seller,productApi.getStock());
		ValidationHelper.validateProduct(product);
		seller.addProduct(product);
    	saveOrUpdate(seller);	
	}
    
    @Transactional
	@Override
	public void updateProductOfSeller(ProductApi productApi) throws SellerNotFoundException,
			ProductNameNotAcceptedException, ProductPriceNotAcceptedException, ProductStockNotAcceptedException, ProductNotFoundException {
    	log.debug("Updating a existing product: {} of seller: {} ",productApi.getIdProduct(), productApi.getIdSeller());
    	Seller seller = getSeller(productApi.getIdSeller());
		Product product = getProductOfSeller(seller, productApi.getIdProduct());
		product.setName(productApi.getName());
		product.setPrice(productApi.getPrice());
		product.setStock(productApi.getStock());
		ValidationHelper.validateProduct(product);
		saveOrUpdate(seller);
	}

	private Product getProductOfSeller(Seller seller, Long idProduct) throws ProductNotFoundException {
		Product product = seller.findProduct(idProduct);
		 if(product == null) {
			 log.debug("Product no found: {} to seller: {} ",idProduct, seller.getIdSeller());
			 throw new ProductNotFoundException();
		}
		return product;
	}

}
