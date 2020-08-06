package ar.com.flexibility.examen.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:messages.properties")
public class MessagesProps {

	// ---------------
	// Attributes
	// ---------------
	@Value("${app.incomplete.fields}")
	private String incompleteFields;
	@Value("${app.server.error}")
	private String serverError;
	@Value("${app.success.transaction}")
	private String sucessTransaction;

	@Value("${client.duplicated}")
	private String clientDuplicated;
	@Value("${client.not.found}")
	private String clientNotFound;
	@Value("${client.purchases.error}")
	private String clientPurchasesError;

	@Value("${product.amount.error}")
	private String productAmountError;
	@Value("${product.amount.min.error}")
	private String productAmountMinError;
	@Value("${product.duplicated}")
	private String productDuplicated;
	@Value("${product.not.found}")
	private String productNotFound;
	@Value("${product.price.error}")
	private String productPriceError;
	@Value("${product.price.min.error}")
	private String productPriceMinError;
	@Value("${product.sales.error}")
	private String productSalesError;
	
	@Value("${sale.already.approved}")
	private String saleAlreadyApproved;
	@Value("${sale.duplicated}")
	private String saleDuplicated;
	@Value("${sale.not.found}")
	private String saleNotFound;
	@Value("${sale.product.amount.error}")
	private String saleProductAmountError;
	@Value("${sale.product.amount.invalid}")
	private String saleProductAmountInvalid;

	@Value("${seller.duplicated}")
	private String sellerDuplicated;
	@Value("${seller.not.found}")
	private String sellerNotFound;
	@Value("${seller.sales.error}")
	private String sellerSalesError;
	
	// ---------------
	// Constructor
	// ---------------

	// ---------------
	// Methods
	// ---------------
	public String getIncompleteFields() {
		return incompleteFields;
	}
	public String getServerError() {
		return serverError;
	}
	public String getSucessTransaction() {
		return sucessTransaction;
	}
	public String getClientDuplicated() {
		return clientDuplicated;
	}
	public String getClientNotFound() {
		return clientNotFound;
	}
	public String getClientPurchasesError() {
		return clientPurchasesError;
	}
	public String getProductAmountError() {
		return productAmountError;
	}
	public String getProductAmountMinError() {
		return productAmountMinError;
	}
	public String getProductDuplicated() {
		return productDuplicated;
	}
	public String getProductNotFound() {
		return productNotFound;
	}
	public String getProductPriceError() {
		return productPriceError;
	}
	public String getProductPriceMinError() {
		return productPriceMinError;
	}
	public String getProductSalesError() {
		return productSalesError;
	}
	public String getSaleAlreadyApproved() {
		return saleAlreadyApproved;
	}
	public String getSaleDuplicated() {
		return saleDuplicated;
	}
	public String getSaleNotFound() {
		return saleNotFound;
	}
	public String getSaleProductAmountError() {
		return saleProductAmountError;
	}
	public String getSaleProductAmountInvalid() {
		return saleProductAmountInvalid;
	}
	public String getSellerDuplicated() {
		return sellerDuplicated;
	}
	public String getSellerNotFound() {
		return sellerNotFound;
	}
	public String getSellerSalesError() {
		return sellerSalesError;
	}

}
