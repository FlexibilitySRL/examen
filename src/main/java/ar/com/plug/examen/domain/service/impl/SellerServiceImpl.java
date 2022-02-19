package ar.com.plug.examen.domain.service.impl;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import javax.xml.bind.ValidationException;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.SellerDto;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.service.SellerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService
{
	private final SellerRepository sellerRepository;

	@Autowired
	public SellerServiceImpl(SellerRepository sellerRepository)
	{
		this.sellerRepository = sellerRepository;
	}

	@Override
	public PageDto<Seller> getActiveSellersPageable(int pageNumber, int pageSize)
	{
		return new PageDto<>(
			this.sellerRepository.findAllByActiveTrue(PageRequest.of(pageNumber, pageSize))
		);
	}

	@Override
	public PageDto<Seller> getAllSellersPageable(int pageNumber, int pageSize)
	{
		return new PageDto<>(
			this.sellerRepository.findAll(PageRequest.of(pageNumber, pageSize))
		);
	}

	@Override
	public Seller getSellerById(Long id) {
		if(Objects.isNull(id)) {
			throw new NoSuchElementException("El id del proveedor no puede ser nulo.");
		}
		Optional<Seller> optionalSeller = this.sellerRepository.findById(id);
		if(optionalSeller.isPresent()) {
			return optionalSeller.get();
		} else {
			throw new NoSuchElementException("El id del proveedor no se encuentra en la base de datos.");
		}
	}

	@Override
	public Seller getSellerByCode(String code) {
		if(StringUtils.isBlank(code)) {
			throw new NoSuchElementException("El código del proveedor no puede ser nulo.");
		}
		Seller sellerToReturn = this.sellerRepository.findByCode(code);
		if(Objects.nonNull(sellerToReturn)) {
			return sellerToReturn;
		} else {
			throw new NoSuchElementException("El código del proveedor no se encuentra en la base de datos.");
		}
	}

	@Override
	public Seller saveSeller(SellerDto sellerDto) throws ValidationException
	{
		if(Objects.isNull(sellerDto)) {
			throw new ValidationException("Los datos para la creación de un proveedor no pueden ser nulos.");
		}
		Seller newSeller = Seller.builder()
			.code(sellerDto.getCode())
			.document(sellerDto.getDocument())
			.description(sellerDto.getDescription())
			.active(sellerDto.getActive())
			.modificationDate(new Date())
			.build();
		return this.sellerRepository.save(newSeller);
	}

	@Override
	public Seller updateSeller(Long id, SellerDto sellerDto) throws ValidationException
	{
		if(Objects.isNull(id) || (Objects.isNull(sellerDto))) {
			throw new ValidationException("Los datos para la actualización de un proveedor no pueden ser nulos.");
		}
		if(this.sellerRepository.existsById(id)) {
			Seller sellerFromDatabase = this.sellerRepository.findByCode(sellerDto.getCode());
			if(Objects.nonNull(sellerFromDatabase) && !sellerFromDatabase.getId().equals(id)) {
				throw new ValidationException("El número de documento ya pertenece a otro proveedor.");
			}
			sellerFromDatabase.setCode(sellerDto.getCode());
			sellerFromDatabase.setDocument(sellerDto.getDocument());
			sellerFromDatabase.setDescription(sellerDto.getDescription());
			sellerFromDatabase.setActive(sellerDto.getActive());
			sellerFromDatabase.setModificationDate(new Date());
			return this.sellerRepository.save(sellerFromDatabase);
		} else {
			throw new NoSuchElementException("El proveedor con el id " + id + " no existe.");
		}
	}

	@Override
	public Seller inactivateSeller(Long id) throws ValidationException
	{
		if(Objects.isNull(id)) {
			throw new ValidationException("Los datos para la actualización de un proveedor no pueden ser nulos.");
		}
		Seller sellerFromDatabase = this.sellerRepository.findById(id)
			.orElseThrow(
				() -> new NoSuchElementException("El proveedor con el id " + id + " no existe.")
			);;
		sellerFromDatabase.setActive(Boolean.FALSE);
		return this.sellerRepository.save(sellerFromDatabase);
	}

	@Override
	public Long deleteSeller(Long id) throws ValidationException
	{
		if(Objects.isNull(id)) {
			throw new ValidationException("Los datos para la actualización de un cliente no pueden ser nulos.");
		}
		if(this.sellerRepository.existsById(id)) {
			this.sellerRepository.deleteById(id);
			return id;
		} else {
			throw new NoSuchElementException("El cliente con el id " + id + " no existe.");
		}
	}
}
