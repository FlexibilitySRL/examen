package ar.com.plug.examen.domain.serviceimpl;

import ar.com.plug.examen.domain.dtos.ClienteDTO;
import ar.com.plug.examen.domain.dtos.CompraDTO;
import ar.com.plug.examen.domain.dtos.VendedorDTO;
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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CompraServiceImpl implements ICompraService{
    private final static Logger LOGGER = Logger.getLogger("domain.serviceimpl.CompraServiceImpl");

    @Autowired
    private CompraMapper compraMapper;
    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private ProductoMapper productoMapper;
    @Autowired
    private VendedorMapper vendedorMapper;
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    private VendedorRepository vendedorRepository;

    @Override
    public List<CompraDTO> getAll() {

        List<CompraDTO> listDto = new ArrayList<>();

        try {
            List<Compra> list= compraRepository.findAll();
            for (Compra c: list
                 ) {
                CompraDTO compradto = new CompraDTO();
                compradto.setCliente(clienteMapper.toDto(clienteRepository.getOne(c.getCliente().getId())));
                compradto.setVendedor(vendedorMapper.toDto(vendedorRepository.getOne(c.getVendedor().getId())));
                compradto.setProductos(c.getProductos().stream().map(productoDTO -> productoMapper.toDto(productoRepository.getOne(productoDTO.getId()))).collect(Collectors.toList()));
                compradto.setImpuestos(c.getImpuestos());
                compradto.setMedioDePago(c.getMedioDePago());
                compradto.setPrecioSinIva(c.getPrecioSinIva());
                listDto.add(compradto);

            }
        } catch (Exception e) {
            LOGGER.info(e.getMessage());

        }
        return listDto;
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
        Compra compra1 = new Compra();

        compra1.setCliente(clienteRepository.findById(compra.getCliente().getId()).get());
        compra1.setVendedor(vendedorRepository.findById(compra.getVendedor().getId()).get());
        compra1.setProductos(compra.getProductos().stream().map(productoDTO -> productoRepository.findById(productoDTO.getId()).get()).collect(Collectors.toList()));
        compra1.setImpuestos(compra.getImpuestos());
        compra1.setMedioDePago(compra.getMedioDePago());
        compra1.setPrecioSinIva(compra.getPrecioSinIva());
        Compra compra2 = compraRepository.save(compra1);
        LOGGER.info("El cliente"+compra2.getCliente().getNombre()+" gasto:"+compra2.getPrecioSinIva());
        return new CompraDTO();
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
