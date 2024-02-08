package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.DTO.VendorDTO;
import ar.com.plug.examen.app.api.VendorApi;
import ar.com.plug.examen.app.mapper.VendorMapper;
import ar.com.plug.examen.domain.service.VendorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/vendors")
public class VendorController {

    private final VendorService vendorService;

    private final VendorMapper mapper;

    @Autowired
    public VendorController(VendorService vendorService, VendorMapper mapper) {
        this.vendorService = vendorService;
        this.mapper = mapper;
    }

    /**
     * Agrega un nuevo vendedor.
     */
    @PostMapping
    public ResponseEntity<VendorDTO> addVendor(@RequestBody VendorApi vendorApi) {
        VendorDTO vendorAdded = vendorService.addVendor(mapper.asDTO(vendorApi));
        log.info("Vendedor agregado con éxito: {}", vendorApi.getName());
        return ResponseEntity.ok(vendorAdded);
    }

    /**
     * Actualiza la información de un vendedor.
     */
    @PutMapping("/{vendorId}")
    public ResponseEntity<VendorDTO> updateVendor(@PathVariable UUID vendorId, @RequestBody VendorApi vendorApi) {
        VendorDTO vendorUpdated = vendorService.updateVendor(vendorId, mapper.asDTO(vendorApi));
        log.info("Vendedor actualizado con éxito: {}", vendorApi.getName());
        return ResponseEntity.ok(vendorUpdated);
    }

    /**
     * Elimina un vendedor.
     */
    @DeleteMapping("/{vendorId}")
    public ResponseEntity<Void> deleteVendor(@PathVariable UUID vendorId) {
        vendorService.deleteVendor(vendorId);
        log.info("Vendedor eliminado con éxito: {}", vendorId);
        return ResponseEntity.ok().build();
    }

    /**
     * Obtiene la lista de todos los vendedores.
     */
    @GetMapping
    public ResponseEntity<List<VendorDTO>> getAllVendors() {
        List<VendorDTO> vendors = vendorService.getAllVendors();
        log.info("Consulta exitosa de todos los vendedores.");
        return ResponseEntity.ok(vendors);
    }

    /**
     * Obtiene el vendedor por id.
     */
    @GetMapping("/{vendorId}")
    public ResponseEntity<VendorDTO> getVendor(@PathVariable UUID vendorId) {
        Optional<VendorDTO> vendor = vendorService.getVendorById(vendorId);
        log.info("Consulta exitosa de todos los vendedores.");
        return vendor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
