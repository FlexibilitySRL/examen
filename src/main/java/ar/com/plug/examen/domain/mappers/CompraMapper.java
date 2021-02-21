package ar.com.plug.examen.domain.mappers;

import ar.com.plug.examen.domain.dtos.ClienteDTO;
import ar.com.plug.examen.domain.dtos.CompraDTO;
import ar.com.plug.examen.domain.models.Cliente;
import ar.com.plug.examen.domain.models.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class CompraMapper {

    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private ProductoMapper productoMapper;
    @Autowired
    private VendedorMapper vendedorMapper;
    private final static Logger LOGGER = Logger.getLogger("domain.serviceimpl.CompraMapper");

    public CompraDTO toDto(Compra compra){
        LOGGER.info("iniciando mapeo a dto");
        CompraDTO dto = new CompraDTO();
        dto.setCliente(clienteMapper.toDto(compra.getCliente()));
        dto.setId(compra.getId());
        dto.setImpuestos(compra.getImpuestos());
        dto.setMedioDePago(compra.getMedioDePago());
        dto.setPrecioSinIva(compra.getPrecioSinIva());
        dto.setVendedor(vendedorMapper.toDto(compra.getVendedor()));
        dto.setProductos(compra.getProductos().stream().map(producto -> productoMapper.toDto(producto)).collect(Collectors.toList()));

        return dto;
    }

    public Compra toModel(CompraDTO compraDTO){
        LOGGER.info("iniciando mapeo a modelo");
        Compra model = new Compra();
        model.setCliente(clienteMapper.toModel(compraDTO.getCliente()));
        model.setId(compraDTO.getId());
        model.setImpuestos(compraDTO.getImpuestos());
        model.setMedioDePago(compraDTO.getMedioDePago());
        model.setPrecioSinIva(compraDTO.getPrecioSinIva());
        model.setVendedor(vendedorMapper.toModel(compraDTO.getVendedor()));
        model.setProductos(compraDTO.getProductos().stream().map(producto -> productoMapper.toModel(producto)).collect(Collectors.toList()));

        return model;
    }
}
