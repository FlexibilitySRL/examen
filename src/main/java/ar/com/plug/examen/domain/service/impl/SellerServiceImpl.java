package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.dto.SellerDTO;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerRepository sellerRepository;
    @Override
    public void createSeller(SellerDTO sellerDTO) {
        Seller sellerToSave = new Seller();
        sellerToSave.setEmail(sellerDTO.getEmail());
        sellerToSave.setDocumentNumber(sellerDTO.getDocumentNumber());
        sellerToSave.setName(sellerDTO.getName());
        sellerToSave.setLastName(sellerDTO.getLastName());
        sellerToSave.setPhone(sellerDTO.getPhone());
        sellerRepository.save(sellerToSave);
    }

    @Override
    public void deleteSeller(Long idSeller) {
        sellerRepository.deleteById(idSeller);
    }

    @Override
    public void editSeller(Long idSeller, SellerDTO sellerDTO) {
        Optional<Seller> sellerResult = sellerRepository.findById(idSeller);
        sellerResult.get().setPhone(sellerDTO.getPhone());
        sellerResult.get().setName(sellerDTO.getName());
        sellerResult.get().setLastName(sellerDTO.getLastName());
        sellerResult.get().setEmail(sellerDTO.getEmail());
        sellerResult.get().setDocumentNumber(sellerDTO.getDocumentNumber());
        sellerRepository.save(sellerResult.get());
    }
}
