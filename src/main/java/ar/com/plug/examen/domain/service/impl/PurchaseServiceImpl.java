package ar.com.plug.examen.domain.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import javax.xml.bind.ValidationException;

import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.PurchaseDto;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.PurchaseDetail;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.repository.PurchaseDetailRepository;
import ar.com.plug.examen.domain.repository.PurchaseRepository;
import ar.com.plug.examen.domain.service.PurchaseService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService
{
	private final PurchaseRepository purchaseRepository;
	private final ClientRepository clientRepository;
	private final PurchaseDetailRepository purchaseDetailRepository;
	private final ProductRepository productRepository;

	@Autowired
	public PurchaseServiceImpl(
		PurchaseRepository purchaseRepository, ClientRepository clientRepository,
		PurchaseDetailRepository purchaseDetailRepository, ProductRepository productRepository)
	{
		this.purchaseRepository = purchaseRepository;
		this.clientRepository = clientRepository;
		this.purchaseDetailRepository = purchaseDetailRepository;
		this.productRepository = productRepository;
	}

	/**
	 * Returns a list of {@link Purchase} in a paginated format according to {@link PageDto} attributes.
	 * All the purchases must have the approved attribute set to true.
	 *
	 * @param pageNumber initial page
	 * @param pageSize   size of the page
	 * @return {@link PageDto} with the active purchases in it.
	 */
	@Override
	public PageDto<Purchase> getApprovedPurchasesPageable(int pageNumber, int pageSize)
	{
		log.debug("[getApprovedPurchasesPageable] page:{}, size:{}", pageNumber, pageSize);
		return new PageDto<>(
			this.purchaseRepository.findAllByApproveTrue(PageRequest.of(pageNumber, pageSize))
		);
	}

	/**
	 * Returns the list of all {@link Purchase} in a paginated format according to {@link PageDto} attributes.
	 *
	 * @param pageNumber initial page
	 * @param pageSize   size of the page
	 * @return {@link PageDto} with all purchases in it.
	 */
	@Override
	public PageDto<Purchase> getAllPurchasesPageable(int pageNumber, int pageSize)
	{
		log.debug("[getAllPurchasesPageable] page:{}, size:{}", pageNumber, pageSize);
		return new PageDto<>(
			this.purchaseRepository.findAll(PageRequest.of(pageNumber, pageSize))
		);
	}

	/**
	 * Returns a {@link Purchase} according to its {@code id}.
	 *
	 * @param id id of the Purchase to query
	 * @return {@link Purchase} with the corresponding {@code id}.
	 */
	@Override
	public Purchase getPurchaseById(Long id)
	{
		if(Objects.isNull(id)) {
			throw new NoSuchElementException("El id de la compra no puede ser nulo.");
		}
		log.debug("[getPurchaseById] id:{}", id);
		Optional<Purchase> optionalPurchase = this.purchaseRepository.findById(id);
		if(optionalPurchase.isPresent()) {
			return optionalPurchase.get();
		} else {
			throw new NoSuchElementException("El id de la compra no se encuentra en la base de datos.");
		}
	}

	/**
	 * Returns a {@link Purchase} according to its {@code receiptNumber} code.
	 *
	 * @param receiptNumber identifier of a purchase
	 * @return {@link Purchase} with the corresponding {@code receiptNumber}.
	 */
	@Override
	public Purchase getPurchaseByReceiptNumber(String receiptNumber)
	{
		if(StringUtils.isBlank(receiptNumber)) {
			throw new NoSuchElementException("El código de la compra no puede ser nula.");
		}
		log.debug("[getPurchaseByReceiptNumber] receipt:{}", receiptNumber);
		Purchase purchaseFromDatabase = this.purchaseRepository.findByReceiptNumber(receiptNumber);
		if(Objects.nonNull(purchaseFromDatabase)) {
			return purchaseFromDatabase;
		} else {
			throw new NoSuchElementException("El código de la compra no se encuentra en la base de datos.");
		}
	}

	/**
	 * Creates a new {@link Purchase} by using the data of the {@link PurchaseDto} passed as parameter.
	 *
	 * @param purchaseDto data which will be use to create a new Purchase.
	 * @return the new created {@link Purchase}
	 * @throws ValidationException    In case of a null parameters or null client.
	 * @throws NoSuchElementException In case of that the client doesn't exist on the database.
	 */
	@Override
	public Purchase savePurchase(PurchaseDto purchaseDto) throws ValidationException
	{
		if(Objects.isNull(purchaseDto)) {
			throw new ValidationException("Los datos para la creación de un compra no pueden ser nulos.");
		}
		if(Objects.isNull(purchaseDto.getClientId())) {
			throw new ValidationException("El id del cliente no puede ser nulo.");
		}
		log.debug("[savePurchase] purchaseDto:{}", purchaseDto);
		Client client = this.clientRepository.findById(purchaseDto.getClientId())
			.orElseThrow(
				() -> new NoSuchElementException("El cliente con el id " + purchaseDto.getClientId() + " no existe.")
			);
		Purchase newPurchase = Purchase.builder()
			.receiptNumber(purchaseDto.getReceiptNumber())
			.total(BigDecimal.ZERO)
			.approve(Boolean.FALSE)
			.client(client)
			.modificationDate(new Date())
			.build();
		return this.purchaseRepository.save(newPurchase);
	}

	/**
	 * Updates a {@link Purchase} by using the data of the {@link PurchaseDto} passed as parameter.
	 *
	 * @param id         identifier to query the Purchase in the database.
	 * @param purchaseDto data which will be use to create a new Purchase.
	 * @return the updated {@link Purchase}
	 * @throws ValidationException    In case of a null parameters or null client.
	 * @throws NoSuchElementException In case of that the client doesn't exist on the database.
	 */
	@Override
	public Purchase updatePurchase(Long id, PurchaseDto purchaseDto) throws ValidationException
	{
		if(Objects.isNull(id) || (Objects.isNull(purchaseDto))) {
			throw new ValidationException("Los datos para la actualización de una compra no pueden ser nulos.");
		}
		log.debug("[updatePurchase] id:{}, purchaseDto:{}", id, purchaseDto);
		if(this.purchaseRepository.existsById(id)) {
			Purchase purchaseFromDatabase = this.purchaseRepository.findByReceiptNumber(purchaseDto.getReceiptNumber());
			if(Objects.nonNull(purchaseFromDatabase) && !purchaseFromDatabase.getId().equals(id)) {
				throw new ValidationException("El número de comprobante ya pertenece a otra compra.");
			}
			Client client = this.clientRepository.findById(purchaseDto.getClientId())
				.orElseThrow(
					() -> new NoSuchElementException(
						"El cliente con el id " + purchaseDto.getClientId() + " no existe.")
				);
			purchaseFromDatabase.setReceiptNumber(purchaseDto.getReceiptNumber());
			//purchaseFromDatabase.setApprove(purchaseDto.getApprove());
			purchaseFromDatabase.setClient(client);
			purchaseFromDatabase.setModificationDate(new Date());
			return this.purchaseRepository.save(purchaseFromDatabase);
		} else {
			throw new NoSuchElementException("La compra con el id " + id + " no existe.");
		}
	}

	/**
	 * Updates a {@link Purchase} by setting the approve attribute to true.
	 *
	 * @param id identifier to query the Purchase in the database.
	 * @return the approved {@link Purchase}
	 * @throws ValidationException    In case of a null parameters or null client.
	 * @throws NoSuchElementException In case of that the purchase doesn't exist on the database.
	 */
	@Override
	public Purchase approvePurchase(Long id) throws ValidationException
	{
		if(Objects.isNull(id)) {
			throw new ValidationException("Los datos para la actualización de la compra no pueden ser nulos.");
		}
		log.debug("[approvePurchase] id: {}", id);
		Purchase purchaseFromDatabase = this.purchaseRepository.findById(id)
			.orElseThrow(
				() -> new NoSuchElementException("La compra con el id " + id + " no existe.")
			);
		List<Product> productsToUpdate = new ArrayList<>();
		List<PurchaseDetail> allDetails = purchaseDetailRepository.findAllByPurchaseId(id);
		if (!allDetails.isEmpty()) {
			for (PurchaseDetail detail : allDetails) {
				if(detail.getQuantity().compareTo(detail.getProduct().getStockQty())>0) {
					throw new ValidationException("La cantidad del producto "+detail.getProduct().getSku()+" excede la existencia en stock.");
				} else {
					Integer stockQty = detail.getProduct().getStockQty();
					Integer detailQty = detail.getQuantity();
					detail.getProduct().setStockQty(stockQty-detailQty);
					productsToUpdate.add(detail.getProduct());
				}
			}
		}
		productRepository.saveAll(productsToUpdate);
		purchaseFromDatabase.setApprove(Boolean.TRUE);
		return this.purchaseRepository.save(purchaseFromDatabase);
	}

	/**
	 * Performs a physical delete of a {@link Purchase}.
	 *
	 * @param id identifier to query the Purchase in the database.
	 * @return the id of the deleted {@link Purchase}
	 * @throws ValidationException    In case of a null parameters.
	 * @throws NoSuchElementException In case of that the purchase doesn't exist on the database.
	 */
	@Override
	public Long deletePurchase(Long id) throws ValidationException
	{
		if(Objects.isNull(id)) {
			throw new ValidationException("Los datos para el borrado de la compra no pueden ser nulos.");
		}
		log.debug("[deletePurchase] id:{}", id);
		if (this.purchaseDetailRepository.existsByPurchaseId(id)){
			throw new ValidationException("Existen detalles de la compra que deben ser borrados previamente.");
		}
		Purchase purchase = this.purchaseRepository.findById(id)
			.orElseThrow(
				() -> new NoSuchElementException("La compra con el id " + id + " no existe.")
			);
		if(purchase.getApprove()) {
			throw new ValidationException("La compra ya fue aprobada y no puede ser borrada.");
		}
		this.purchaseRepository.deleteById(id);
		return id;
	}
}
