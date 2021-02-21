package ar.com.plug.examen.domain.serviceimpl;

import ar.com.plug.examen.domain.dtos.CompraDTO;
import ar.com.plug.examen.domain.mappers.ClienteMapper;
import ar.com.plug.examen.domain.mappers.CompraMapper;
import ar.com.plug.examen.domain.mappers.ProductoMapper;
import ar.com.plug.examen.domain.mappers.VendedorMapper;
import ar.com.plug.examen.domain.models.Compra;
import ar.com.plug.examen.domain.models.Producto;
import ar.com.plug.examen.domain.repository.ClienteRepository;
import ar.com.plug.examen.domain.repository.CompraRepository;
import ar.com.plug.examen.domain.repository.ProductoRepository;
import ar.com.plug.examen.domain.repository.VendedorRepository;
import ar.com.plug.examen.domain.services.ICompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CompraServiceImpl implements ICompraService{
    private final static Logger LOGGER = Logger.getLogger("domain.serviceimpl.CompraServiceImpl");

    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CompraMapper compraMapper;

    @Override
    public List<CompraDTO> getAll() {
        List<CompraDTO> list = null;
        try {
            list= compraRepository.findAll().stream().map(compra -> compraMapper.toDto(compra)).collect(Collectors.toList());

        } catch (Exception e) {
            LOGGER.info(e.getMessage());

        }
        return list;
    }

    @Override
    @Transactional
    public CompraDTO save(CompraDTO compra) throws Exception {
        compra.getProductos().forEach(productoDTO -> {
            Producto producto = productoRepository.findById(productoDTO.getId()).get();
            int stock = producto.getStock() - 1;
            if (stock >= 0) {
                producto.setStock(stock);
                productoRepository.save(producto);
                LOGGER.info(producto.getNombre()+" Stock:"+producto.getStock());
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        });
        LOGGER.info("El cliente"+compra.getCliente().getNombre()+" gasto:"+compra.getPrecioSinIva());
        return compraMapper.toDto(compraRepository.save(compraMapper.toModel(compra)));


    }

    @Override
    public void delete(CompraDTO compra) {
        try{
            compraRepository.delete(compraMapper.toModel(compra));
            LOGGER.info("Se borro la compra: "+compra.getId());
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
    }
}
