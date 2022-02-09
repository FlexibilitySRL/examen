package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.SellerDTO;
import ar.com.plug.examen.domain.converter.SellerConverter;
import ar.com.plug.examen.domain.exception.SellerFoundException;
import ar.com.plug.examen.domain.exception.SellerNotFoundException;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Implementation of {@link SellerService}
 */
@Service
public class SellerServiceImpl implements SellerService
{

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SellerConverter sellerConverter;

    @Override
    @Transactional
    public SellerDTO save(SellerDTO sellerDTO)
    {
        Optional<Seller> seller1 = sellerRepository
              .findByDocumentIdAndDocumentType(sellerDTO.getDocumentId(), sellerDTO.getDocumentType());

        if (seller1.isPresent()) {
            throw new SellerFoundException("The seller is already registered");
        }

        Seller seller = sellerRepository.save(sellerConverter.toModel(sellerDTO));
        return sellerConverter.toDTO(seller);
    }


    @Override
    public List<SellerDTO> getAllSellers()
    {
        return sellerRepository.findAll()
              .stream()
              .map(sellerConverter::toDTO)
              .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SellerDTO getSellerById(Long id)
    {
        return sellerConverter.toDTO(getSellerByIdIfExists(id));
    }

    @Override
    @Transactional
    public SellerDTO update(SellerDTO sellerDTO)
    {
        getSellerByIdIfExists(sellerDTO.getId());
        return sellerConverter.toDTO(sellerRepository.save(sellerConverter.toModel(sellerDTO)));
    }

    @Override
    @Transactional
    public void delete(Long id)
    {
        sellerRepository.delete(getSellerByIdIfExists(id));
    }

    /**
     * Get Seller by id, if exists return Seller Model, if not, throws exception.

     * @param id
     * @return Seller
     */
    private Seller getSellerByIdIfExists(Long id) {

        return sellerRepository.findById(id)
              .orElseThrow(() -> new SellerNotFoundException("Seller with Id "+id+" not found"));
    }
}
