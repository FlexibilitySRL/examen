package ar.com.plug.examen.domain.serviceimpl;

import ar.com.plug.examen.domain.dtos.ProductoDTO;
import ar.com.plug.examen.domain.mappers.ProductoMapper;
import ar.com.plug.examen.domain.repository.ProductoRepository;
import ar.com.plug.examen.domain.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements IProductoService {
    private final static Logger LOGGER = Logger.getLogger("domain.serviceimpl.ProductoServiceImpl");

    @Autowired
    private ProductoMapper productoMapper;
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> getAll() {
        return productoRepository.findAll().stream().map(producto -> productoMapper.toDto(producto)).collect(Collectors.toList());
    }

    @Override
    public ProductoDTO save(ProductoDTO producto) {
        try {
            return productoMapper.toDto(productoRepository.save(productoMapper.toModel(producto)));
        }catch (Exception e){
            LOGGER.info(e.getMessage());
            return null;

        }
    }

    @Override
    public void delete(ProductoDTO producto) {
        productoRepository.delete(productoMapper.toModel(producto));
    }
}
