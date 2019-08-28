package ar.com.flexibility.examen.app.service;

import javax.persistence.EntityNotFoundException;

import ar.com.flexibility.examen.domain.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressService {

	Page<Address> getAddresses(Pageable pageable);

	Page<Address> getAddresses(Long customerId, Pageable pageable) throws EntityNotFoundException;

	Address getAddress(Long customerId, Long addressId) throws EntityNotFoundException;

	Address createAddress(Long customerId, Address address) throws EntityNotFoundException;

	Address updateAddress(Long customerId, Long addressId, Address address) throws EntityNotFoundException, IllegalArgumentException;

	String deleteAddress(Long customerId, Long addressId) throws EntityNotFoundException, IllegalArgumentException;

}
