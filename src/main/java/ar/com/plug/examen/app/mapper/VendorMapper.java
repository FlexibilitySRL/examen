package ar.com.plug.examen.app.mapper;

import ar.com.plug.examen.app.DTO.VendorDTO;
import ar.com.plug.examen.app.api.VendorApi;
import ar.com.plug.examen.domain.model.Vendor;
import org.springframework.stereotype.Component;

@Component
public class VendorMapper {

    public VendorDTO asDTO(VendorApi source) {
        return new VendorDTO(null, source.getName());
    }

    public VendorDTO asDTO(Vendor source) {
        return new VendorDTO(source.getId(), source.getName());
    }
}