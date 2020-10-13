package ar.com.plug.examen.app.rest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.app.api.PurchaseApi;
import ar.com.plug.examen.app.api.PurchaseItemApi;
import ar.com.plug.examen.constants.BusinessExceptionConstants;
import ar.com.plug.examen.constants.PaymentsConstants;
import ar.com.plug.examen.domain.enums.PurchaseStatus;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.service.IClientService;
import ar.com.plug.examen.domain.service.IProductService;
import ar.com.plug.examen.domain.service.IPurchaseService;
import ar.com.plug.examen.domain.service.ISellerService;
import ar.com.plug.examen.domain.utils.PaymentsSubsystemTranslator;
import ar.com.plug.examen.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Purchases Service")
@RestController
@RequestMapping(path = "/purchases")
public class PurchaseController extends AbstractPaymentsController {
	
	@Autowired
	private IPurchaseService purchaseService;
	
	@Autowired
	private IClientService clientService;
	
	@Autowired
	private ISellerService sellerService;
	
	@Autowired
	private IProductService productService;
	
	@ApiOperation(value = "Find all purchases")
	@GetMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> list() {
		List<Purchase> purchaseList = purchaseService.findAll();
		return buildResponseGet(PaymentsSubsystemTranslator.getInstance().getPurchaseApiList(purchaseList));
	}
	
	@ApiOperation(value = "Approve a purchase by purchase id", notes = "Change a purchase status from PENDING to APPROVE")
	@PutMapping(path = "/approve/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> approve(@PathVariable Long id) {
		try {
			
			Purchase purchase = purchaseService.changeStatus(id, PurchaseStatus.APPROVED);
			return buildResponseGet(PaymentsSubsystemTranslator.getInstance().getPurchaseApi(purchase));
			
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.UNEXPECTED_EXCEPTION_REQUEST, null), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "Bulk Approve", notes = "Change all purchases status from PENDING to APPROVED for a single client id")
	@PutMapping(path = "/bulkApproveByClient/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> bulkApproveByClient(@PathVariable Long id) {
		try {
			
			Client client = clientService.findById(id);
			if (client == null) {
				return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.ENTITY_NOT_FOUND, new String[]{PaymentsConstants.CLIENT_ENTITY, id.toString()}), null, HttpStatus.NOT_FOUND);
			}
			
			List<Purchase> purchases = purchaseService.bulkChangeStatus(client, PurchaseStatus.APPROVED);
			return buildResponseGet(PaymentsSubsystemTranslator.getInstance().getPurchaseApiList(purchases));
			
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.UNEXPECTED_EXCEPTION_REQUEST, null), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "Cancel a purchase by purchase id", notes = "Change a purchase status from PENDING to CANCELED")
	@PutMapping(path = "/cancel/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> cancel(@PathVariable Long id) {
		try {
			
			Purchase purchase = purchaseService.changeStatus(id, PurchaseStatus.CANCELED);
			return buildResponseGet(PaymentsSubsystemTranslator.getInstance().getPurchaseApi(purchase));
			
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.UNEXPECTED_EXCEPTION_REQUEST, null), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "Bulk Cancel", notes = "Change all purchases status from PENDING to CANCELED for a single client")
	@PutMapping(path = "/bulkCancelByClient/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> bulkCancelByClient(@PathVariable Long id) {
		try {

			Client client = clientService.findById(id);
			if (client == null) {
				return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.ENTITY_NOT_FOUND, new String[]{PaymentsConstants.CLIENT_ENTITY, id.toString()}), null, HttpStatus.NOT_FOUND);
			}
			
			List<Purchase> purchases = purchaseService.bulkChangeStatus(client, PurchaseStatus.CANCELED);
			return buildResponseGet(PaymentsSubsystemTranslator.getInstance().getPurchaseApiList(purchases));
			
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.UNEXPECTED_EXCEPTION_REQUEST, null), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "Retrieve Purchases list", notes = "Find purchases for a single client id")
	@GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> findByClient(@PathVariable Long id) {
		try {
			
			Client client = clientService.findById(id);
			if (client == null) {
				return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.ENTITY_NOT_FOUND, new String[]{PaymentsConstants.CLIENT_ENTITY, id.toString()}), null, HttpStatus.NOT_FOUND);
			}
			
			List<Purchase> purchaseList = purchaseService.findByClient(client);
			return buildResponseGet(PaymentsSubsystemTranslator.getInstance().getPurchaseApiList(purchaseList));
			
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.UNEXPECTED_EXCEPTION_REQUEST, null), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@ApiOperation(value = "Retrieve Purchases list by status", notes = "Find purchases for a single client id and status (PENDING, APPROVED, CANCELED)")
	@GetMapping(path = "/{id}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> findByClientAndStatus(@PathVariable Long id, @PathVariable String status) {
		try {
			
			Client client = clientService.findById(id);
			if (client == null) {
				return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.ENTITY_NOT_FOUND, new String[]{PaymentsConstants.CLIENT_ENTITY, id.toString()}), null, HttpStatus.NOT_FOUND);
			}
			
			PurchaseStatus statusEnum = null;
			try {
				statusEnum = PurchaseStatus.valueOf(status);
			} catch (Exception e) {
				e.printStackTrace();
				BusinessException exc = new BusinessException(e.getMessage());
				return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.STATUS_NOT_VALID, new String[]{status}), exc, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			List<Purchase> purchaseList = purchaseService.findByClientAndStatus(client, statusEnum);
			return buildResponseGet(PaymentsSubsystemTranslator.getInstance().getPurchaseApiList(purchaseList)); 
			
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.UNEXPECTED_EXCEPTION_REQUEST, null), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "Create a Purchase")
	@PostMapping(path = "", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> create(@Valid @RequestBody PurchaseApi purchaseApi, BindingResult result) {
		try {
			if (result.hasErrors()) {
				return buildResponseValidation(result);
			}
			
			this.validatePurchase(purchaseApi);
			
			Purchase purchase = purchaseService.buy(purchaseApi.getDescription(), purchaseApi.getClient(), purchaseApi.getSeller(), getItemsMap(purchaseApi.getItems()));
			return buildResponseGet(PaymentsSubsystemTranslator.getInstance().getPurchaseApi(purchase));
			
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.UNEXPECTED_EXCEPTION_REQUEST, null), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void validatePurchase(PurchaseApi purchaseApi) {
		Client client = clientService.findById(purchaseApi.getClient());
		if (client == null) {
			throw new BusinessException(messageSourceProvider.get(BusinessExceptionConstants.ENTITY_NOT_FOUND, new String[]{PaymentsConstants.CLIENT_ENTITY, purchaseApi.getClient().toString()}));
		}
		
		Seller seller = sellerService.findById(purchaseApi.getSeller());
		if (seller == null) {
			throw new BusinessException(messageSourceProvider.get(BusinessExceptionConstants.ENTITY_NOT_FOUND, new String[]{PaymentsConstants.SELLER_ENTITY, purchaseApi.getSeller().toString()}));
		}
		
		purchaseApi.getItems().forEach((i) -> {
			Product product = productService.findById(i.getProduct());
			if (product == null) {
				throw new BusinessException(messageSourceProvider.get(BusinessExceptionConstants.ENTITY_NOT_FOUND, new String[]{PaymentsConstants.PRODUCT_ENTITY, purchaseApi.getSeller().toString()}));
			}
		});
	}

	private Map<Long, Integer> getItemsMap(List<PurchaseItemApi> itemList) {
		Map<Long, Integer> map = itemList.stream().collect(Collectors.toMap(PurchaseItemApi::getProduct, PurchaseItemApi::getQuantity));
		return map;
	}
	
	@ApiOperation(value = "Delete a Purchase", notes = "Delete a Purchase by id")
	@DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			purchaseService.delete(id);
			return buildResponseDelete(PaymentsConstants.PURCHASE_ENTITY);
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.UNEXPECTED_EXCEPTION_REQUEST, null), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}