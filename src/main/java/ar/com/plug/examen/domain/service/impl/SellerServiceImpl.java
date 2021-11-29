package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.dto.SellerDto;
import ar.com.plug.examen.domain.exceptions.GenericBadRequestException;
import ar.com.plug.examen.domain.exceptions.GenericNotFoundException;
import ar.com.plug.examen.domain.mappers.SellerMapper;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.service.SellerService;
import ar.com.plug.examen.domain.validators.Validator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private Validator validator;

    /**
     * (non-Javadoc)
     *
     * @see ar.com.plug.examen.domain.service.SellerService#findAll()
     */
    @Override
    public List<SellerDto> findAll() {
        return this.sellerMapper.sellersToListSellerApi(this.sellerRepository.findAll());
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * ar.com.plug.examen.domain.service.SellerService#findByIdChecked(Long)
     */
    @Override
    public SellerDto findByIdChecked(Long id) throws GenericNotFoundException {
        return this.sellerMapper.sellerToSellerApi(this.sellerRepository.findById(id)
                .orElseThrow(() -> new GenericNotFoundException("Seller not found")));
    }

    /**
     * (non-Javadoc)
     *
     * @see ar.com.plug.examen.domain.service.SellerService#save(SellerDto)
     */
    @Override
    @Transactional
    public SellerDto save(SellerDto sellerApi) throws GenericBadRequestException {
        this.validator.validateSeller(sellerApi, Boolean.FALSE);
        return this.sellerMapper.sellerToSellerApi(
                this.sellerRepository.save(this.sellerMapper.sellerApiToSeller(sellerApi)));
    }

    /**
     * (non-Javadoc)
     *
     * @see ar.com.plug.examen.domain.service.SellerService#update(SellerDto)
     */
    @Override
    @Transactional
    public SellerDto update(SellerDto sellerApi)
            throws GenericNotFoundException, GenericBadRequestException {
        this.validator.validateSeller(sellerApi, Boolean.TRUE);
        this.sellerRepository.findById(sellerApi.getId())
                .orElseThrow(() -> new GenericNotFoundException("Seller not found"));
        return this.sellerMapper.sellerToSellerApi(
                this.sellerRepository.save(this.sellerMapper.sellerApiToSeller(sellerApi)));
    }

    /**
     * (non-Javadoc)
     *
     * @see ar.com.plug.examen.domain.service.SellerService#delete(Long)
     */
    @Override
    public void delete(Long id) throws GenericNotFoundException {
        this.sellerRepository.findById(id)
                .orElseThrow(() -> new GenericNotFoundException("Seller not found"));
        this.sellerRepository.deleteById(id);
    }
}
