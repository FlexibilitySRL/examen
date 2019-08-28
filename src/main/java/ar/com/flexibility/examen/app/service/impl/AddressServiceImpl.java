package ar.com.flexibility.examen.app.service.impl;

import javax.persistence.EntityNotFoundException;

import ar.com.flexibility.examen.domain.model.Address;
import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.repository.AddressRepository;
import ar.com.flexibility.examen.app.service.AddressService;
import ar.com.flexibility.examen.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	CustomerService customerService;


	@Override
	public Page<Address> getAddresses(Pageable pageable) {
		return addressRepository.findAll(new PageRequest(pageable.getPageNumber(), pageable.getPageSize()));
	}

	@Override
	public Page<Address> getAddresses(Long customerId, Pageable pageable) throws EntityNotFoundException {
		customerService.getCustomerById(customerId);
		return addressRepository.findAddressByCustomerId(customerId, new PageRequest(pageable.getPageNumber(), pageable.getPageSize()));
	}

	@Override
	public Address getAddress(Long customerId, Long addressId) throws EntityNotFoundException {
		Address address = addressRepository.findOne(addressId);
		if (address == null) {
			throw new EntityNotFoundException("Address with id: " + addressId + " does not exists");
		}
		return address;
	}

	@Override
	public Address createAddress(Long customerId, Address address) throws EntityNotFoundException {
		// TODO add: if customerId == logged user
		Customer customer = customerService.getCustomerById(customerId);
		address.setCustomer(customer);
		return addressRepository.save(address);
	}

	@Override
	public Address updateAddress(Long customerId, Long addressId, Address address) throws EntityNotFoundException, IllegalArgumentException {
		// TODO add: if customerId == logged user
		Customer customer = customerService.getCustomerById(customerId);
		Address dbAddress = this.getAddress(customer.getId(), addressId);
		if (dbAddress.getCustomer().getId().equals(customer.getId())) {
			dbAddress.setCity(address.getCity());
			dbAddress.setCountry(address.getCountry());
			dbAddress.setProvince(address.getProvince());
			dbAddress.setDirection(address.getDirection());
			return addressRepository.save(dbAddress);
		} else {
			throw new IllegalArgumentException("The address with id: " + addressId
					+ " does not belong to the customer with id: " + customer.getId());
		}
	}

	@Override
	public String deleteAddress(Long customerId, Long addressId) throws EntityNotFoundException, IllegalArgumentException {
		// TODO add: if customerId == logged user
		Customer customer = customerService.getCustomerById(customerId);
		Address address = this.getAddress(customer.getId(), addressId);
		if (address.getCustomer().getId().equals(customer.getId())) {
			addressRepository.delete(address.getId());
			return "The address with id: " + address.getId() + " was deleted.";
		} else {
			throw new IllegalArgumentException("The address with id: " + addressId
					+ " does not belong to the customer with id: " + customer.getId());
		}
	}

}
