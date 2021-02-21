package ar.com.plug.examen.domain.serviceimpl;

import ar.com.plug.examen.domain.dtos.VendedorDTO;
import ar.com.plug.examen.domain.mappers.VendedorMapper;
import ar.com.plug.examen.domain.repository.VendedorRepository;
import ar.com.plug.examen.domain.services.IVendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class VendedorServiceImpl implements IVendedorService {
    private final static Logger LOGGER = Logger.getLogger("domain.serviceimpl.VendedorServiceImpl");

    @Autowired
    private VendedorRepository vendedorRepository;
    @Autowired
    VendedorMapper vendedorMapper;

    @Override
    public List<VendedorDTO> getAll() {
        try {
            return vendedorRepository.findAll().stream().map(vendedor -> vendedorMapper.toDto(vendedor)).collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    @Override
    public VendedorDTO save(VendedorDTO vendedorDTO) {
        try{
            return vendedorMapper.toDto(vendedorRepository.save(vendedorMapper.toModel(vendedorDTO)));
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(VendedorDTO vendedorDTO) {
        try{
            vendedorRepository.delete(vendedorMapper.toModel(vendedorDTO));
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
    }

    //Getters and Setters

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public VendedorRepository getVendedorRepository() {
        return vendedorRepository;
    }

    public void setVendedorRepository(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public VendedorMapper getVendedorMapper() {
        return vendedorMapper;
    }

    public void setVendedorMapper(VendedorMapper vendedorMapper) {
        this.vendedorMapper = vendedorMapper;
    }
}
