package ar.com.plug.examen.app.rest;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.PurchaseDetailDto;
import ar.com.plug.examen.domain.model.PurchaseDetail;
import ar.com.plug.examen.domain.service.PurchaseDetailService;
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
@Tag(name = "Purchase Detail")
public class PurchaseDetailController
{
	private final PurchaseDetailService purchaseDetailService;

	@Autowired
	public PurchaseDetailController(PurchaseDetailService purchaseDetailService){
		this.purchaseDetailService = purchaseDetailService;
	}

	@Operation(summary = "Gets a paginated list of purchase details")
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
	@GetMapping(value = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
	public PageDto<PurchaseDetail> allDetails(
		@Parameter(description = "Number of the starting page", example = "0")
		@RequestParam(defaultValue = "0") int page,
		@Parameter(description = "Amount of items per page", example = "5")
		@RequestParam(defaultValue = "5") int size)
	{
		return this.purchaseDetailService.getAllDetailsPageable(page, size);
	}

	@Operation(summary = "Gets a purchase detail by its id")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = PurchaseDetail.class)
			)
		}
	)
	@GetMapping(value = "/detail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PurchaseDetail purchaseById(
		@Parameter(description = "Purchase's detail internal id", example = "2")
		@PathVariable Long id)
	{
		return this.purchaseDetailService.getPurchaseDetailById(id);
	}

	@Operation(summary = "Gets a purchase by the associated purchase-id")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = PurchaseDetail.class)
			)
		}
	)
	@GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
	public PageDto<PurchaseDetail> purchaseDetailByPurchaseId(
		@Parameter(description = "Number of the starting page", example = "0")
		@RequestParam(defaultValue = "0") int page,
		@Parameter(description = "Amount of items per page", example = "5")
		@RequestParam(defaultValue = "5") int size,
		@Parameter(description = "Associated product id", example = "1")
		@RequestParam Long purchaseId)
	{
		return this.purchaseDetailService.getDetailsByPurchaseIdPageable(purchaseId, page, size);
	}

	@Operation(summary = "Creates a new purchase detail.")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = PurchaseDetail.class)
			)
		}
	)
	@PostMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
	public PurchaseDetail createDetail(@RequestBody @Valid PurchaseDetailDto purchaseDetailDto) throws ValidationException
	{
		return this.purchaseDetailService.saveDetail(purchaseDetailDto);
	}

	@Operation(summary = "Updates a purchase detail.")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = PurchaseDetail.class)
			)
		}
	)
	@PutMapping(value = "/detail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PurchaseDetail updateDetail(
		@Parameter(description = "Purchase detail internal id", example = "2")
		@PathVariable Long id,
		@RequestBody @Valid PurchaseDetailDto purchaseDetailDto) throws ValidationException
	{
		return this.purchaseDetailService.updateDetail(id, purchaseDetailDto);
	}

	@Operation(summary = "Delete a purchase detail physically from the database")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = PurchaseDetail.class)
			)
		}
	)
	@DeleteMapping(value = "/detail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Long deletePurchaseDetail(
		@Parameter(description = "Purchase detail internal id", example = "2")
		@PathVariable Long id) throws ValidationException
	{
		return this.purchaseDetailService.deletePurchaseDetail(id);
	}
}
