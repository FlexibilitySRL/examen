package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.DTO.VendorDTO;
import ar.com.plug.examen.app.mapper.VendorMapper;
import ar.com.plug.examen.domain.model.Vendor;
import ar.com.plug.examen.domain.repository.VendorRepository;
import ar.com.plug.examen.domain.service.VendorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of the VendorService interface.
 */
@Slf4j
@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper mapper;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper mapper) {
        this.vendorRepository = vendorRepository;
        this.mapper = mapper;
    }

    /**
     * Adds a new vendor.
     *
     * @param vendorDTO The vendor data to be added.
     */
    @Override
    public VendorDTO addVendor(VendorDTO vendorDTO) {
        log.info("Adding vendor: {}", vendorDTO.name());
        Vendor vendor = new Vendor(vendorDTO.name());
        return mapper.asDTO(vendorRepository.save(vendor));
    }

    /**
     * Updates an existing vendor with the given ID.
     *
     * @param vendorId  The unique identifier of the vendor to be updated.
     * @param vendorDTO The new data for the vendor.
     */
    @Override
    public VendorDTO updateVendor(UUID vendorId, VendorDTO vendorDTO) {
        log.info("Updating vendor with ID {}: {}", vendorId, vendorDTO.name());

        Vendor existingVendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new EntityNotFoundException("Vendor not found with ID: " + vendorId));

        Vendor updatedVendor = new Vendor(existingVendor.getId(), vendorDTO.name(), existingVendor.getStatus());

        return mapper.asDTO(vendorRepository.save(updatedVendor));
    }

    /**
     * Deletes an existing vendor with the given ID.
     *
     * @param vendorId The unique identifier of the vendor to be deleted.
     */
    @Override
    public void deleteVendor(UUID vendorId) {
        log.info("Deleting vendor with ID: {}", vendorId);
        vendorRepository.deleteById(vendorId);
    }

    /**
     * Retrieves a list of all vendors.
     *
     * @return List of VendorDTO representing all vendors.
     */
    @Override
    public List<VendorDTO> getAllVendors() {
        log.info("Fetching all vendors");
        List<Vendor> vendors = vendorRepository.findAll();
        return vendors.stream()
                .map(mapper::asDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a vendor by its ID.
     *
     * @param vendorId The unique identifier of the vendor.
     * @return Optional containing VendorDTO if found, empty otherwise.
     */
    @Override
    public Optional<VendorDTO> getVendorById(UUID vendorId) {
        log.info("Fetching vendor by ID: {}", vendorId);
        Optional<Vendor> vendorOptional = vendorRepository.findById(vendorId);

        return vendorOptional.map(mapper::asDTO);
    }
}
