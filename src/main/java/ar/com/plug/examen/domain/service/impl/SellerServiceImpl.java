package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.dto.SellerDTO;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.ISellerRepository;
import ar.com.plug.examen.domain.service.SellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    Logger logger = LoggerFactory.getLogger(SellerServiceImpl.class);

    @Autowired
    private ISellerRepository sellerRepository;

    @Override
    public SellerDTO save(SellerDTO sellerDTO) {

        logger.info("Saving seller: " + sellerDTO.getName());
        return sellerRepository.save(sellerDTO);
    }

    @Override
    public SellerDTO update(SellerDTO sellerDTO) {

        logger.info("Updating seller: " + sellerDTO.getName());
        return sellerRepository.update(sellerDTO);
    }

    @Override
    public void delete(long sellerId) {
      sellerRepository.delete(sellerId);
    }

    @Override
    public Optional<SellerDTO> findById(long sellerId) {

        logger.info("Find seller by id: " + sellerId);
        return sellerRepository.findById(sellerId);
    }

    @Override
    public Optional<List<SellerDTO>> getAllActive()
    {
        logger.info("Find all active sellers");
        return sellerRepository.getAllActive();
    }

    @Override
    public List<SellerDTO> getAll() {

        logger.info("Find all sellers");
        return sellerRepository.getAll();
    }
}
