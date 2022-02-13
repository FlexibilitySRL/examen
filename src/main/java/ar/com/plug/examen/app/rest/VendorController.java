package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.VendorApi;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Vendor;
import ar.com.plug.examen.domain.service.VendorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("vendors")
@RequiredArgsConstructor
@Tag(name = "Vendor service", description = "Vendor CRUD")
public class VendorController {

    private final VendorService vendorService;

    @GetMapping
    public ResponseEntity<List<Vendor>> getVendors() {
        return new ResponseEntity<>(vendorService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable("id") long id) {
        return new ResponseEntity<>(vendorService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Vendor> createVendor(@Valid @RequestBody VendorApi vendorApi) {
        return new ResponseEntity<>(vendorService.save(new Vendor(vendorApi.getName(), vendorApi.getSsn())), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendor> updateVendor(@PathVariable("id") long id, @Valid @RequestBody VendorApi vendorApi) {

        Vendor vendor = vendorService.findById(id);
        vendor.setName(vendorApi.getName());
        vendor.setSsn(vendorApi.getSsn());
        return new ResponseEntity<>(vendorService.save(vendor), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteVendor(@PathVariable("id") long id) {
        vendorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
