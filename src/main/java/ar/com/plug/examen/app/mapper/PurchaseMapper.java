package ar.com.plug.examen.app.mapper;

import ar.com.plug.examen.app.DTO.PurchaseDTO;
import ar.com.plug.examen.app.api.PurchaseApi;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.Vendor;
import ar.com.plug.examen.domain.repository.CartRepository;
import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.domain.repository.VendorRepository;
import ar.com.plug.examen.exception.CustomerNotFoundException;
import ar.com.plug.examen.exception.VendorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PurchaseMapper {

    final CustomerMapper customerMapper;
    final VendorMapper vendorMapper;
    final CartRepository cartRepository;
    final CustomerRepository customerRepository;
    final VendorRepository vendorRepository;


    public PurchaseDTO asDTO(PurchaseApi source) {
        Customer customer = customerRepository.findById(source.getCustomerId())
                .orElseThrow(CustomerNotFoundException::new);
        Vendor vendor = vendorRepository.findById(source.getVendorId())
                .orElseThrow(VendorNotFoundException::new);
        return new PurchaseDTO(null, customerMapper.asDTO(customer), vendorMapper.asDTO(vendor), Purchase.PurchaseStatus.valueOf(String.valueOf(source.getStatusApi())), source.getPriceApi());
    }

    public PurchaseDTO asDTO(Purchase source) {
        return new PurchaseDTO(source.getId(), customerMapper.asDTO(source.getCustomer()), vendorMapper.asDTO(source.getVendor()), source.getStatus(), source.getPrice());
    }

}
