package ar.com.plug.examen.domain.mappers;

import ar.com.plug.examen.domain.dtos.ProductoDTO;
import ar.com.plug.examen.domain.models.Producto;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ProductoMapper {

    private final static Logger LOGGER = Logger.getLogger("domain.serviceimpl.ProductoMapper");

    public ProductoDTO toDto(Producto producto){
        LOGGER.info("iniciando mapeo a dto");
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setMarca(producto.getMarca());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setTamanio(producto.getTamanio());
        dto.setStock(producto.getStock());

        return dto;
    }

    public Producto toModel(ProductoDTO productoDTO){
        LOGGER.info("iniciando mapeo a model");
        Producto model = new Producto();
        model.setId(productoDTO.getId());
        model.setMarca(productoDTO.getMarca());
        model.setNombre(productoDTO.getNombre());
        model.setPrecio(productoDTO.getPrecio());
        model.setTamanio(productoDTO.getTamanio());
        model.setStock(productoDTO.getStock());

        return model;
    }
}
