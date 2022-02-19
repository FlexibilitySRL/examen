package ar.com.plug.examen.app.rest;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.SellerDto;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.service.SellerService;
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
@Tag(name = "Sellers")
public class SellerController
{
	private final SellerService sellerService;

	@Autowired
	public SellerController(SellerService sellerService)
	{
		this.sellerService = sellerService;
	}

	@Operation(summary = "Gets a paginated list of sellers")
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
	@GetMapping(value = "/sellers", produces = MediaType.APPLICATION_JSON_VALUE)
	public PageDto<Seller> allSellers(
		@Parameter(description = "Number of the starting page", example = "0")
		@RequestParam(defaultValue = "0") int page,
		@Parameter(description = "Amount of items per page", example = "5")
		@RequestParam(defaultValue = "5") int size)
	{
		return this.sellerService.getAllSellersPageable(page, size);
	}

	@Operation(summary = "Gets a paginated list of active sellers")
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
	@GetMapping(value = "/sellers/active", produces = MediaType.APPLICATION_JSON_VALUE)
	public PageDto<Seller> activeSellers(
		@Parameter(description = "Number of the starting page", example = "0")
		@RequestParam(defaultValue = "0") int page,
		@Parameter(description = "Amount of items per page", example = "5")
		@RequestParam(defaultValue = "5") int size)
	{
		return this.sellerService.getActiveSellersPageable(page, size);
	}

	@Operation(summary = "Gets a seller by its code number")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = Seller.class)
			)
		}
	)
	@GetMapping(value = "/seller", produces = MediaType.APPLICATION_JSON_VALUE)
	public Seller sellerByCode(
		@Parameter(description = "Seller's code number", example = "3509091")
		@RequestParam String code)
	{
		return this.sellerService.getSellerByCode(code);
	}

	@Operation(summary = "Gets a seller by its id")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = Seller.class)
			)
		}
	)
	@GetMapping(value = "/seller/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Seller sellerByDocument(
		@Parameter(description = "Seller's internal id", example = "2")
		@PathVariable Long id)
	{
		return this.sellerService.getSellerById(id);
	}

	@Operation(summary = "Creates a new seller.")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = Seller.class)
			)
		}
	)
	@PostMapping(value = "/seller", produces = MediaType.APPLICATION_JSON_VALUE)
	public Seller createSeller(@RequestBody @Valid SellerDto sellerDto) throws ValidationException
	{
		return this.sellerService.saveSeller(sellerDto);
	}

	@Operation(summary = "Updates the information of a seller.")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = Seller.class)
			)
		}
	)
	@PutMapping(value = "/seller/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Seller updateSeller(
		@Parameter(description = "Seller's internal id", example = "2")
		@PathVariable Long id,
		@RequestBody @Valid SellerDto dto) throws ValidationException
	{
		return this.sellerService.updateSeller(id, dto);
	}

	@Operation(summary = "Delete a seller logically by inactivate him.")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = Seller.class)
			)
		}
	)
	@PutMapping(value = "/seller/inactivate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Seller inactivateSeller(
		@Parameter(description = "Seller's internal id", example = "2")
		@PathVariable Long id) throws ValidationException
	{
		return this.sellerService.inactivateSeller(id);
	}

	@Operation(summary = "Delete a seller physically from the database")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(
		responseCode = "200",
		content = {
			@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = Seller.class)
			)
		}
	)
	@DeleteMapping(value = "/seller/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Long deleteSeller(
		@Parameter(description = "Seller's internal id", example = "2")
		@PathVariable Long id) throws ValidationException
	{
		return this.sellerService.deleteSeller(id);
	}
}
