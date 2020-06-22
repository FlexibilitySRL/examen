package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.app.api.VendorApi;
import ar.com.flexibility.examen.domain.exceptions.BadRequestException;
import ar.com.flexibility.examen.domain.exceptions.NotFoundException;
import ar.com.flexibility.examen.domain.mappers.ApiMapper;
import ar.com.flexibility.examen.domain.mappers.EntityMapper;
import ar.com.flexibility.examen.domain.model.Vendor;
import ar.com.flexibility.examen.domain.repository.VendorRepository;
import ar.com.flexibility.examen.domain.service.VendorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class VendorServiceImpl implements VendorService {
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private EntityMapper entityMapper;
    @Autowired
    private ApiMapper apiMapper;

    @Override
    public List<VendorApi> all() {
        log.trace("Retrieving all vendors from vendor repository.");
        List<Vendor> vendors = vendorRepository.findAll();
        return entityMapper.toVendorsApi(vendors);
    }

    @Override
    public VendorApi get(Long id) throws NotFoundException {
        log.trace("Retrieving vendor from repository with id: {}", id);
        Vendor vendor = vendorRepository.findOne(id);
        if (vendor == null)
            throw new NotFoundException("Vendor with id " + id);
        return entityMapper.toVendorApi(vendor);
    }

    @Override
    public VendorApi create(VendorApi vendorApi) throws BadRequestException {
        log.trace("Saving to vendor repository vendor: {}", vendorApi);
        Vendor vendor = vendorRepository.save(apiMapper.toVendor(vendorApi));
        return entityMapper.toVendorApi(vendor);
    }

    @Override
    public VendorApi update(Long id, VendorApi vendorApi) throws BadRequestException, NotFoundException {
        log.trace("Updating vendor with id {} in vendor repository with vendor: {}", id, vendorApi);
        Vendor vendor = vendorRepository.findOne(id);
        if (vendor == null)
            throw new NotFoundException("Vendor with id " + id);
        vendor.setName(vendorApi.getName());
        return entityMapper.toVendorApi(vendorRepository.save(vendor));
    }

    @Override
    public void remove(Long id) throws NotFoundException {
        log.trace("Deleting id {} from vendor repository", id);
        if (!vendorRepository.exists(id)) {
            throw new NotFoundException("Vendor id " + id + "doesn't exist");
        }
        vendorRepository.delete(id);
    }
}
