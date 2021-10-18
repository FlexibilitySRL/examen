package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.dto.SellerDTO;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.ISellerRepository;
import ar.com.plug.examen.domain.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private ISellerRepository sellerRepository;

    @Override
    public SellerDTO save(SellerDTO sellerDTO) {
        return sellerRepository.save(sellerDTO);
    }

    @Override
    public SellerDTO update(SellerDTO sellerDTO) {
        return sellerRepository.update(sellerDTO);
    }

    @Override
    public void delete(long sellerId) {
      sellerRepository.delete(sellerId);
    }

    @Override
    public Optional<SellerDTO> findById(long sellerId) {
        return sellerRepository.findById(sellerId);
    }

    @Override
    public Optional<List<SellerDTO>> getAllActive() {
        return sellerRepository.getAllActive();
    }

    @Override
    public List<SellerDTO> getAll() {
        return sellerRepository.getAll();
    }
}
