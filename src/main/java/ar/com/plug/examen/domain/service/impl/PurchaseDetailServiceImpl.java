package ar.com.plug.examen.domain.service.impl;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import javax.xml.bind.ValidationException;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.PurchaseDetailDto;
import ar.com.plug.examen.app.api.PurchaseDto;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.PurchaseDetail;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.repository.PurchaseDetailRepository;
import ar.com.plug.examen.domain.repository.PurchaseRepository;
import ar.com.plug.examen.domain.service.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PurchaseDetailServiceImpl implements PurchaseDetailService
{
	private final PurchaseDetailRepository purchaseDetailRepository;
	private final PurchaseRepository purchaseRepository;
	private final ProductRepository productRepository;

	@Autowired
	public PurchaseDetailServiceImpl(PurchaseDetailRepository purchaseDetailRepository, PurchaseRepository purchaseRepository, ProductRepository productRepository){
		this.purchaseDetailRepository = purchaseDetailRepository;
		this.purchaseRepository = purchaseRepository;
		this.productRepository = productRepository;
	}

	/**
	 * Returns a list of {@link PurchaseDetail} in a paginated format according to {@link PageDto} attributes of
	 * a given purchase id passed in the parameters.
	 *
	 * @param purchaseId id of the purchase to query
	 * @param pageNumber initial page
	 * @param pageSize   size of the page
	 * @return {@link PageDto} with the details of a purchase.
	 */
	@Override
	public PageDto<PurchaseDetail> getDetailsByPurchaseIdPageable(Long purchaseId, int pageNumber, int pageSize)
	{
		return new PageDto<>(
			this.purchaseDetailRepository.findAllByPurchaseId(purchaseId, PageRequest.of(pageNumber, pageSize))
		);
	}

	/**
	 * Returns a list of {@link PurchaseDetail} in a paginated format according to {@link PageDto} attributes of
	 * a given product id passed in the parameters.
	 *
	 * @param productId id of the product to query
	 * @param pageNumber initial page
	 * @param pageSize   size of the page
	 * @return {@link PageDto} with the details of a purchase.
	 */
	@Override
	public PageDto<PurchaseDetail> getDetailsByProductIdPageable(Long productId, int pageNumber, int pageSize)
	{
		return new PageDto<>(
			this.purchaseDetailRepository.findAllByProductId(productId, PageRequest.of(pageNumber, pageSize))
		);
	}

	/**
	 * Returns the list of all {@link PurchaseDetail} in a paginated format according to {@link PageDto} attributes.
	 *
	 * @param pageNumber initial page
	 * @param pageSize   size of the page
	 * @return {@link PageDto} with all purchase's details in it.
	 */
	@Override
	public PageDto<PurchaseDetail> getAllDetailsPageable(int pageNumber, int pageSize)
	{
		return new PageDto<>(
			this.purchaseDetailRepository.findAll(PageRequest.of(pageNumber, pageSize))
		);
	}

	/**
	 * Returns a {@link PurchaseDetail} according to its {@code id}.
	 *
	 * @param id id of the PurchaseDetail to query
	 * @return {@link PurchaseDetail} with the corresponding {@code id}.
	 */
	@Override
	public PurchaseDetail getPurchaseDetailById(Long id) {
		if(Objects.isNull(id)) {
			throw new NoSuchElementException("El id del detalle no puede ser nulo.");
		}
		Optional<PurchaseDetail> optionalPurchase = this.purchaseDetailRepository.findById(id);
		if(optionalPurchase.isPresent()) {
			return optionalPurchase.get();
		} else {
			throw new NoSuchElementException("El id del detalle no se encuentra en la base de datos.");
		}
	}

	/**
	 * Creates a new {@link PurchaseDetail} by using the data of the {@link PurchaseDetailDto} passed as parameter.
	 *
	 * @param detailDto data which will be use to create a new detail of a purchase.
	 * @return the new created {@link PurchaseDetail}
	 * @throws ValidationException    In case of a null parameters or null purchase or product.
	 * @throws NoSuchElementException In case of that the purchase or product doesn't exist on the database.
	 */
	@Override
	public PurchaseDetail saveDetail(PurchaseDetailDto detailDto) throws ValidationException
	{
		if(Objects.isNull(detailDto)) {
			throw new ValidationException("Los datos para la creación de un detalle de compra no pueden ser nulos.");
		}
		if(Objects.isNull(detailDto.getPurchaseId())) {
			throw new ValidationException("El id del la compra no puede ser nulo.");
		}
		if(Objects.isNull(detailDto.getProductId())) {
			throw new ValidationException("El id del producto no puede ser nulo.");
		}
		Purchase purchaseFromDatabase = this.purchaseRepository.findById(detailDto.getPurchaseId())
			.orElseThrow(
				() -> new NoSuchElementException("La compra con el id " + detailDto.getPurchaseId() + " no existe.")
			);
		Product productFromDatabase = this.productRepository.findById(detailDto.getProductId())
			.orElseThrow(
				() -> new NoSuchElementException("El producto con el id " + detailDto.getProductId() + " no existe.")
			);
		PurchaseDetail newDetail = PurchaseDetail.builder()
			.product(productFromDatabase)
			.quantity(detailDto.getQuantity())
			.purchase(purchaseFromDatabase)
			.unitSalePrice(detailDto.getUnitSalePrice())
			.totalSalePrice(detailDto.getTotalSalePrice())
			.modificationDate(new Date())
			.build();
		return this.purchaseDetailRepository.save(newDetail);
	}

	/**
	 * Updates a {@link PurchaseDetail} by using the data of the {@link PurchaseDto} passed as parameter.
	 *
	 * @param id         identifier to query the PurchaseDetail in the database.
	 * @param detailDto data which will be used to create a new PurchaseDetail.
	 * @return the updated {@link PurchaseDetail}
	 * @throws ValidationException    In case of a null parameters or null purchase or product.
	 * @throws NoSuchElementException In case of that the provided ids doesn't exist on the database.
	 */
	@Override
	public PurchaseDetail updateDetail(Long id, PurchaseDetailDto detailDto) throws ValidationException
	{
		if(Objects.isNull(detailDto)) {
			throw new ValidationException("Los datos para la creación de un detalle de compra no pueden ser nulos.");
		}
		if(Objects.isNull(detailDto.getPurchaseId())) {
			throw new ValidationException("El id del la compra no puede ser nulo.");
		}
		if(Objects.isNull(detailDto.getProductId())) {
			throw new ValidationException("El id del producto no puede ser nulo.");
		}
		PurchaseDetail purchaseDetailFromDatabase = this.purchaseDetailRepository.findById(id)
			.orElseThrow(
				() -> new NoSuchElementException("El detalle con el id " + id + " no existe.")
			);
		Purchase purchaseFromDatabase = this.purchaseRepository.findById(detailDto.getPurchaseId())
			.orElseThrow(
				() -> new NoSuchElementException("La compra con el id " + detailDto.getPurchaseId() + " no existe.")
			);
		Product productFromDatabase = this.productRepository.findById(detailDto.getProductId())
			.orElseThrow(
				() -> new NoSuchElementException("El producto con el id " + detailDto.getProductId() + " no existe.")
			);
		if (purchaseFromDatabase.getApprove()) {
			throw new ValidationException("La compra ya fue aprobada, no pueden modificarse los detalles.");
		}
		purchaseDetailFromDatabase.setProduct(productFromDatabase);
		purchaseDetailFromDatabase.setQuantity(detailDto.getQuantity());
		purchaseDetailFromDatabase.setPurchase(purchaseFromDatabase);
		purchaseDetailFromDatabase.setUnitSalePrice(detailDto.getUnitSalePrice());
		purchaseDetailFromDatabase.setTotalSalePrice(detailDto.getTotalSalePrice());
		purchaseDetailFromDatabase.setModificationDate(new Date());

		return this.purchaseDetailRepository.save(purchaseDetailFromDatabase);
	}

	/**
	 * Performs a physical delete of a {@link PurchaseDetail}.
	 *
	 * @param id identifier to query the PurchaseDetail in the database.
	 * @return the id of the deleted {@link PurchaseDetail}
	 * @throws ValidationException    In case of a null parameters.
	 * @throws NoSuchElementException In case of that the purchaseDetail doesn't exist on the database.
	 */
	@Override
	public Long deletePurchaseDetail(Long id) throws ValidationException
	{
		if(Objects.isNull(id)) {
			throw new ValidationException("Los datos para el borrado de del detalle de la compra no pueden ser nulos.");
		}
		if(this.purchaseDetailRepository.existsById(id)) {
			this.purchaseDetailRepository.deleteById(id);
			return id;
		} else {
			throw new NoSuchElementException("El detalle de la compra con el id " + id + " no existe.");
		}
	}
}
