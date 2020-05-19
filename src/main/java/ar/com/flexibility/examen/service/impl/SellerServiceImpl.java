package ar.com.flexibility.examen.service.impl;

import ar.com.flexibility.examen.app.dto.SellerDTO;
import ar.com.flexibility.examen.model.Seller;
import ar.com.flexibility.examen.model.repository.SellerRepository;
import ar.com.flexibility.examen.service.SellerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createSeller(SellerDTO sellerDTO) {

        sellerRepository.save(mapDtoToEntity(sellerDTO));
    }

    @Override
    public Seller retrieveSellerById(Long id) {
        Optional<Seller> seller = sellerRepository.findById(id);
        if(seller.isPresent()) {
            return seller.get();
        }
        return null;
    }

    @Override
    public void updateSeller(Long id , SellerDTO sellerDTO){
        sellerDTO.setId(id);
     sellerRepository.save(mapDtoToEntity(sellerDTO));
    }

    @Override
    public void deleteSellerById(Long id) {
        sellerRepository.deleteById(id);
    }

    private Seller mapDtoToEntity(SellerDTO sellerDTO) {
        return modelMapper.map(sellerDTO,Seller.class);
    }
}
