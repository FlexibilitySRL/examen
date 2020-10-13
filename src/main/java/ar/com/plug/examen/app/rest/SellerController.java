package ar.com.plug.examen.app.rest;

import java.util.List;

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

import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.constants.BusinessExceptionConstants;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.service.ISellerService;
import ar.com.plug.examen.domain.utils.PaymentsSubsystemTranslator;
import io.swagger.annotations.Api;

@Api(value = "Sellers CRUD")
@RestController
@RequestMapping(path = "/sellers")
public class SellerController extends AbstractPaymentsController {
	
	public static final String SELLER_ENTITY = "Seller";
	
	@Autowired
	private ISellerService sellerService;
	
	@GetMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> list() {
		List<Seller> sellerList = sellerService.findAll();
		return buildResponseGet(PaymentsSubsystemTranslator.getInstance().getSellerApiList(sellerList));
	}
	
	@GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> get(@PathVariable Long id) {
		try {
			
			Seller seller= sellerService.findById(id);
			if (seller == null) {
				return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.ENTITY_NOT_FOUND, new String[]{SELLER_ENTITY, id.toString()}), null, HttpStatus.NOT_FOUND);
			}
		
			return buildResponseGet(PaymentsSubsystemTranslator.getInstance().getSellerApi(seller));
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.UNEXPECTED_EXCEPTION_REQUEST, null), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> create(@Valid @RequestBody SellerApi sellerApi, BindingResult result) {
		try {
			if (result.hasErrors()) {
				return buildResponseValidation(result);
			}
			
			Seller seller = PaymentsSubsystemTranslator.getInstance().getSeller(sellerApi);
			
			sellerApi = PaymentsSubsystemTranslator.getInstance().getSellerApi(sellerService.save(seller));
			return buildResponseCreate(SELLER_ENTITY, sellerApi);
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.UNEXPECTED_EXCEPTION_REQUEST, null), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, 
	consumes = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> update(@Valid @RequestBody SellerApi sellerApi, BindingResult result, @PathVariable Long id ) {
		try {
			if (result.hasErrors()) {
				return buildResponseValidation(result);
			}
			
			Seller persistedSeller = sellerService.findById(id);
			
			if (persistedSeller == null) {
				return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.ENTITY_NOT_FOUND, new String[]{SELLER_ENTITY, id.toString()}), null, HttpStatus.NOT_FOUND);
			}
			
			persistedSeller.setName(sellerApi.getName());
			persistedSeller.setEmail(sellerApi.getEmail());
			Seller updatedClient = sellerService.save(persistedSeller);
			
			sellerApi = PaymentsSubsystemTranslator.getInstance().getSellerApi(updatedClient);
			return buildResponseUpdate(SELLER_ENTITY, sellerApi);
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.UNEXPECTED_EXCEPTION_REQUEST, null), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			sellerService.delete(id);
			return buildResponseDelete(SELLER_ENTITY);
		} catch (Exception e) {
			e.printStackTrace();
			return buildResponseError(messageSourceProvider.get(BusinessExceptionConstants.UNEXPECTED_EXCEPTION_REQUEST, null), e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
