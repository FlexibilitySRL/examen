package ar.com.plug.examen.domain.mappers;

import ar.com.plug.examen.domain.dtos.VendedorDTO;
import ar.com.plug.examen.domain.models.Vendedor;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class VendedorMapper {

    private final static Logger LOGGER = Logger.getLogger("domain.serviceimpl.VendedorMapper");

    public VendedorDTO toDto(Vendedor vendedor){
        LOGGER.info("iniciando mapeo a dto");
        VendedorDTO dto = new VendedorDTO();
        dto.setApellido(vendedor.getApellido());
        dto.setDocumento(vendedor.getDocumento());
        dto.setId(vendedor.getId());
        dto.setNombre(vendedor.getNombre());
        dto.setNumLegajo(vendedor.getNumLegajo());

        return dto;
    }

    public Vendedor toModel(VendedorDTO vendedorDTO){
        LOGGER.info("iniciando mapeo a model");
        Vendedor model = new Vendedor();
        model.setApellido(vendedorDTO.getApellido());
        model.setDocumento(vendedorDTO.getDocumento());
        model.setId(vendedorDTO.getId());
        model.setNombre(vendedorDTO.getNombre());
        model.setNumLegajo(vendedorDTO.getNumLegajo());
        return model;
    }
}
