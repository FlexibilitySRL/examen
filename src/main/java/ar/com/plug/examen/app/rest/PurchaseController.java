package ar.com.plug.examen.app.rest;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.PurchaseDto;
import ar.com.plug.examen.aspect.LogController;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@Tag(name = "Purchase")
public class PurchaseController
{
	private final PurchaseService purchaseService;

	@Autowired
	public PurchaseController(PurchaseService purchaseService)
	{
		this.purchaseService = purchaseService;
	}

	@Operation(summary = "Gets a paginated list of purchases")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = PageDto.class)
			)
		}
	)
	@LogController
	@GetMapping(value = "/purchases", produces = MediaType.APPLICATION_JSON_VALUE)
	public PageDto<Purchase> allPurchases(
		@Parameter(description = "Number of the starting page", example = "0")
		@RequestParam(defaultValue = "0") int page,
		@Parameter(description = "Amount of items per page", example = "5")
		@RequestParam(defaultValue = "5") int size)
	{
		return this.purchaseService.getAllPurchasesPageable(page, size);
	}

	@Operation(summary = "Gets a paginated list of active purchases")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = PageDto.class)
			)
		}
	)
	@LogController
	@GetMapping(value = "/purchases/active", produces = MediaType.APPLICATION_JSON_VALUE)
	public PageDto<Purchase> activePurchases(
		@Parameter(description = "Number of the starting page", example = "0")
		@RequestParam(defaultValue = "0") int page,
		@Parameter(description = "Amount of items per page", example = "5")
		@RequestParam(defaultValue = "5") int size)
	{
		return this.purchaseService.getApprovedPurchasesPageable(page, size);
	}

	@Operation(summary = "Gets a purchase by its receiptNumber")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = Purchase.class)
			)
		}
	)
	@LogController
	@GetMapping(value = "/purchase", produces = MediaType.APPLICATION_JSON_VALUE)
	public Purchase purchaseByReceiptNumber(
		@Parameter(description = "Purchase's receiptNumber", example = "receiptNumber-231")
		@RequestParam String receiptNumber)
	{
		return this.purchaseService.getPurchaseByReceiptNumber(receiptNumber);
	}

	@Operation(summary = "Gets a purchase by its id")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = Purchase.class)
			)
		}
	)
	@LogController
	@GetMapping(value = "/purchase/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Purchase purchaseById(
		@Parameter(description = "Purchase's internal id", example = "2")
		@PathVariable Long id)
	{
		return this.purchaseService.getPurchaseById(id);
	}

	@Operation(summary = "Creates a new purchase.")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = Purchase.class)
			)
		}
	)
	@LogController
	@PostMapping(value = "/purchase", produces = MediaType.APPLICATION_JSON_VALUE)
	public Purchase createPurchase(@RequestBody @Valid PurchaseDto purchaseDto) throws ValidationException
	{
		return this.purchaseService.savePurchase(purchaseDto);
	}

	@Operation(summary = "Updates the information of a purchase.")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = Purchase.class)
			)
		}
	)
	@LogController
	@PutMapping(value = "/purchase/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Purchase updatePurchase(
		@Parameter(description = "Purchase's internal id", example = "2")
		@PathVariable Long id,
		@RequestBody @Valid PurchaseDto dto) throws ValidationException
	{
		return this.purchaseService.updatePurchase(id, dto);
	}

	@Operation(summary = "Approves a purchase.")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = Purchase.class)
			)
		}
	)
	@LogController
	@PutMapping(value = "/purchase/approve/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Purchase approvePurchase(
		@Parameter(description = "Purchase's internal id", example = "2")
		@PathVariable Long id) throws ValidationException
	{
		return this.purchaseService.approvePurchase(id);
	}

	@Operation(summary = "Delete a purchase physically from the database")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = Purchase.class)
			)
		}
	)
	@LogController
	@DeleteMapping(value = "/purchase/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Long deletePurchase(
		@Parameter(description = "Purchase's internal id", example = "2")
		@PathVariable Long id) throws ValidationException
	{
		return this.purchaseService.deletePurchase(id);
	}
}
