package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.core.service.AbstractService;
import ar.com.flexibility.examen.domain.model.Seller;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Servicio particular para administrar la entidad Seller
 */
@Service
public class SellerService extends AbstractService<Seller> {
    private static final Logger logger = Logger.getLogger(SellerService.class);

    @Override
    public Logger getLogger() {
        return logger;
    }
}
