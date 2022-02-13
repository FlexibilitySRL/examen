package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.exception.VendorNotFoundException;
import ar.com.plug.examen.domain.model.Vendor;
import ar.com.plug.examen.domain.repository.VendorRepository;
import ar.com.plug.examen.domain.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    @Override
    public List<Vendor> getAll() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor save(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor findById(long id) {
        return vendorRepository.findById(id).orElseThrow(VendorNotFoundException::new);
    }

    @Override
    public void deleteById(long id) {
        vendorRepository.deleteById(id);
    }
}
