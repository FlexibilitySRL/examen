package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.aspects.LogAnnotation;
import ar.com.plug.examen.domain.Repository.SellerRepository;
import ar.com.plug.examen.domain.exceptions.GenericBadRequestException;
import ar.com.plug.examen.domain.exceptions.GenericNotFoundException;
import ar.com.plug.examen.domain.mappers.SellerMapper;
import ar.com.plug.examen.domain.service.SellerService;
import ar.com.plug.examen.domain.validators.Validator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@LogAnnotation
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
  public List<SellerApi> findAll() {
    return this.sellerMapper.sellersToListSellerApi(this.sellerRepository.findAll());
  }

  /**
   * (non-Javadoc)
   *
   * @see ar.com.plug.examen.domain.service.SellerService#findByIdChecked(Long)
   */
  @Override
  public SellerApi findByIdChecked(Long id) throws GenericNotFoundException {
    return this.sellerMapper.sellerToSelerApi(this.sellerRepository.findById(id)
        .orElseThrow(() -> new GenericNotFoundException("Seller not found")));
  }

  /**
   * (non-Javadoc)
   *
   * @see ar.com.plug.examen.domain.service.SellerService#save(SellerApi)
   */
  @Override
  @Transactional
  public SellerApi save(SellerApi sellerApi) throws GenericBadRequestException {
    this.validator.validateSeller(sellerApi, Boolean.FALSE);
    return this.sellerMapper.sellerToSelerApi(
        this.sellerRepository.save(this.sellerMapper.sellerApiToSeller(sellerApi)));
  }

  /**
   * (non-Javadoc)
   *
   * @see ar.com.plug.examen.domain.service.SellerService#update(SellerApi)
   */
  @Override
  @Transactional
  public SellerApi update(SellerApi sellerApi)
      throws GenericNotFoundException, GenericBadRequestException {
    this.validator.validateSeller(sellerApi, Boolean.TRUE);
    this.sellerRepository.findById(sellerApi.getId())
        .orElseThrow(() -> new GenericNotFoundException("Seller not found"));
    return this.sellerMapper.sellerToSelerApi(
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
