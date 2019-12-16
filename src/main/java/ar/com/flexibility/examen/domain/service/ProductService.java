package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.core.service.AbstractService;
import ar.com.flexibility.examen.domain.model.Product;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * Servicio particular para administrar la entidad Product
 *
 */
@Service
public class ProductService extends AbstractService<Product> {
    private static final Logger logger = Logger.getLogger(ProductService.class);

    @Override
    public Logger getLogger() {
        return logger;
    }
}
