package ar.com.flexibility.examen.domain.repository;

import ar.com.flexibility.examen.domain.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

	Page<Address> findAddressByCustomerId(Long customerId, Pageable pageable);

}
