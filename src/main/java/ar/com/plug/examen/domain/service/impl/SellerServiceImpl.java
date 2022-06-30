package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.domain.execptions.NotFoundException;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repositories.SellerRepository;
import ar.com.plug.examen.domain.service.SellerService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public SellerApi createSeller(SellerApi sellerApi) {
        Seller seller = sellerRepository.save(modelMapper.map(sellerApi, Seller.class));
        log.info("The seller " + seller.getId() + " was succesfully created.");
        return modelMapper.map(seller, SellerApi.class);
    }

    @Override
    public SellerApi getSellerById(Long id) throws NotFoundException {

        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("", "The seller with the id:" + id + " was not found."));

        return modelMapper.map(seller, SellerApi.class);
    }


    @Override
    public void removeSeller(Long id) throws NotFoundException {
        if (!sellerRepository.existsById(id)) {
            log.error("The seller with the id:" + id + " does not exist.");
            throw new NotFoundException("", "The seller with id " + id + " does not exist");
        }
        sellerRepository.deleteById(id);
        log.info("The seller " + id + " was succesfully deleted.");

    }

    @Override
    public SellerApi updateSeller(Long id, SellerApi sellerApi) throws NotFoundException {


        if (!sellerRepository.existsById(id)) {
            log.error("The seller with the id:" + id + " does not exist.");
            throw new NotFoundException("", "seller with id " + id + " does not exist");
        }

        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("", "The seller with the id:" + id + " was not found."));

        seller.setName(sellerApi.getName());
        seller = sellerRepository.save(modelMapper.map(seller, Seller.class));
        log.info("The seller " + id + " was succesfully updated.");
        return modelMapper.map(seller, SellerApi.class);
    }

}











