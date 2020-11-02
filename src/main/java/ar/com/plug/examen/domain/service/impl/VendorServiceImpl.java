package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;

import ar.com.plug.examen.domain.mapper.DTOMapper;
import ar.com.plug.examen.domain.model.Vendor;
import ar.com.plug.examen.domain.repository.VendorRepository;

@Service
public class VendorServiceImpl {
	@Autowired
	VendorRepository vendorRepo;

	private DTOMapper dtoMapper = DTOMapper.INSTANCE;

	/**
	 * Creates the vendor.
	 *
	 * @param entity the entity
	 * @return the vendor
	 */
	public Vendor createVendor(@Valid Vendor entity) {
		return vendorRepo.save(entity);
	}

	/**
	 * Delete vendor.
	 *
	 * @param vendorId the vendor id
	 */
	public void deleteVendor(long vendorId) {
		try {
			vendorRepo.deleteById(vendorId);
		} catch (EmptyResultDataAccessException ex) {
			throw new EntityNotFoundException();
		}
	}

	/**
	 * Update vendor.
	 *
	 * @param vendorId  the vendor id
	 * @param newEntity the new entity
	 * @return the vendor
	 */
	public Vendor updateVendor(long vendorId, Vendor newEntity) {

		Vendor oldEntity = vendorRepo.findById(vendorId).orElseThrow(EntityNotFoundException::new);

		dtoMapper.updateVendor(newEntity, oldEntity);

		oldEntity = vendorRepo.save(oldEntity);

		return oldEntity;
	}

	/**
	 * Método que busca un único Vendor por Id. Si no lo encuentra, levanta una
	 * excepción EntityNotFoundException que luego es mapeada al objeto Error
	 * documentado en la API.
	 * 
	 * @param vendorId
	 * @return
	 */
	public Vendor getVendor(long vendorId) {
		return vendorRepo.findById(vendorId).orElseThrow(EntityNotFoundException::new);
	}

	/**
	 * Gets the vendors.
	 *
	 * @param vendorId the vendor id
	 * @return the vendors
	 */
	public List<Vendor> getVendors() {
		return ImmutableList.copyOf(vendorRepo.findAll());
	}
}
