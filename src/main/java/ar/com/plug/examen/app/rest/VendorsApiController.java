package ar.com.plug.examen.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import ar.com.plug.examen.domain.mapper.DTOMapper;
import ar.com.plug.examen.domain.model.Vendor;
import ar.com.plug.examen.domain.service.impl.VendorServiceImpl;
import ar.com.plug.generated.api.VendorsApi;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-10-29T10:08:36.293-03:00[America/Argentina/Buenos_Aires]")

@Slf4j
@Controller
@RequestMapping("${openapi.paymentManagement.base-path:}")
public class VendorsApiController implements VendorsApi {

	private final NativeWebRequest request;

	private final VendorServiceImpl vendorService;

	private DTOMapper dtoMapper = DTOMapper.INSTANCE;

	@Autowired
	public VendorsApiController(NativeWebRequest request, VendorServiceImpl vendorService) {
		this.request = request;
		this.vendorService = vendorService;
	}

	@Override
	public Optional<NativeWebRequest> getRequest() {
		return Optional.ofNullable(request);
	}

	/**
	 * Creates the vendor.
	 *
	 * @param vendor the vendor
	 * @return the response entity
	 */
	@Override
	public ResponseEntity<ar.com.plug.generated.model.Vendor> createVendor(
			@Valid ar.com.plug.generated.model.Vendor vendorDTO) {

		Vendor vendorEntity = dtoMapper.from(vendorDTO);

		vendorEntity = vendorService.createVendor(vendorEntity);
		vendorDTO = dtoMapper.from(vendorEntity);

		return ResponseEntity.ok().body(vendorDTO);

	}

	/**
	 * Delete vendor.
	 *
	 * @param vendorId the vendor id
	 * @return the response entity
	 */
	@Override
	public ResponseEntity<Void> deleteVendor(Integer vendorId) {
		vendorService.deleteVendor(vendorId.longValue());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/**
	 * Update vendor.
	 *
	 * @param vendorId  the vendor id
	 * @param vendorDTO the vendor DTO
	 * @return the response entity
	 */
	@Override
	public ResponseEntity<ar.com.plug.generated.model.Vendor> updateVendor(Integer vendorId,
			ar.com.plug.generated.model.@Valid Vendor vendorDTO) {
		log.info("Se recibe una solicitud de creación de vendor {}", vendorDTO);

		Vendor vendorEntity = dtoMapper.from(vendorDTO);

		vendorEntity = vendorService.updateVendor(vendorId.longValue(), vendorEntity);
		vendorDTO = dtoMapper.from(vendorEntity);

		return ResponseEntity.ok().body(vendorDTO);
	}

	/**
	 * Gets the vendor.
	 *
	 * @param vendorId the vendor id
	 * @return the vendor
	 */
	@Override
	public ResponseEntity<ar.com.plug.generated.model.Vendor> getVendor(Integer vendorId) {
		return ResponseEntity.ok().body(dtoMapper.from(vendorService.getVendor(vendorId.longValue())));
	}

	/**
	 * Gets the vendors.
	 * 
	 * TODO: Admitir filtros de búsqueda.
	 * 
	 * TODO: Paginar
	 * 
	 * TODO: Ordenar
	 * 
	 * @return the vendors
	 */
	@Override
	public ResponseEntity<List<ar.com.plug.generated.model.Vendor>> getVendors() {

		List<ar.com.plug.generated.model.Vendor> vendorList = vendorService.getVendors().stream()
				.map((vendorEntity) -> dtoMapper.from(vendorEntity)).collect(Collectors.toList());

		return ResponseEntity.ok(vendorList);

	}
}
