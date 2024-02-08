package ar.com.plug.examen.domain.service;


import ar.com.plug.examen.app.DTO.VendorDTO;
import ar.com.plug.examen.app.mapper.VendorMapper;
import ar.com.plug.examen.domain.model.Vendor;
import ar.com.plug.examen.domain.repository.VendorRepository;
import ar.com.plug.examen.domain.service.impl.VendorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VendorServiceTest {

    @Mock
    private VendorRepository vendorRepository;

    @Mock
    private VendorMapper mapper;

    private VendorService vendorService;

    @BeforeEach
    public void setup() {
        vendorService = new VendorServiceImpl(vendorRepository, mapper);
    }

    @Test
    public void testAddVendor() {
        // Arrange
        VendorDTO vendorDTO = new VendorDTO(null, "Test Vendor");
        Vendor savedVendor = new Vendor( "Test Vendor");

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        // Act
        vendorService.addVendor(vendorDTO);

        // Assert
        verify(vendorRepository, times(1)).save(any(Vendor.class));
    }

    @Test
    public void testUpdateVendor() {
        // Arrange
        UUID vendorId = UUID.randomUUID();
        VendorDTO updatedVendorDTO = new VendorDTO(vendorId, "Updated Vendor");
        Vendor existingVendor = new Vendor("Original Vendor");
        Vendor updatedVendor = new Vendor(updatedVendorDTO.name());

        when(vendorRepository.findById(vendorId)).thenReturn(Optional.of(existingVendor));
        when(vendorRepository.save(any(Vendor.class))).thenReturn(updatedVendor);
        when(mapper.asDTO(any(Vendor.class))).thenReturn(updatedVendorDTO);

        // Act
        VendorDTO actualVendorDTO = vendorService.updateVendor(vendorId, updatedVendorDTO);

        // Assert
        verify(vendorRepository, times(1)).findById(vendorId);
        verify(vendorRepository, times(1)).save(any(Vendor.class));
        assertEquals("Updated Vendor", actualVendorDTO.name());
    }

    @Test
    public void testDeleteVendor() {
        // Arrange
        UUID vendorId = UUID.randomUUID();

        // Act
        vendorService.deleteVendor(vendorId);

        // Assert
        verify(vendorRepository, times(1)).deleteById(vendorId);
    }

    @Test
    public void testGetAllVendors() {
        // Arrange
        List<Vendor> vendorList = Arrays.asList(
                new Vendor("Vendor 1"),
                new Vendor("Vendor 2")
        );

        when(vendorRepository.findAll()).thenReturn(vendorList);

        // Act
        List<VendorDTO> result = vendorService.getAllVendors();

        // Assert
        verify(vendorRepository, times(1)).findAll();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetVendorById() {
        // Arrange
        UUID vendorId = UUID.randomUUID();
        Vendor vendor = new Vendor("Test Vendor");

        when(vendorRepository.findById(vendorId)).thenReturn(Optional.of(vendor));

        // Act
        Optional<VendorDTO> result = vendorService.getVendorById(vendorId);

        // Assert
        verify(vendorRepository, times(1)).findById(vendorId);
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(vendorId, result.get().id());
    }
}

