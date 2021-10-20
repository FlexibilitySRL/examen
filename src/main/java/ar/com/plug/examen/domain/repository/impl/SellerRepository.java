package ar.com.plug.examen.domain.repository.impl;

import ar.com.plug.examen.domain.crud.SellerCrudRepository;
import ar.com.plug.examen.domain.dto.SellerDTO;
import ar.com.plug.examen.domain.mapper.SellerMapper;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.ISellerRepository;
import ar.com.plug.examen.utils.SellerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SellerRepository implements ISellerRepository {

    @Autowired
    private SellerCrudRepository sellerCrudRepository;

    @Autowired
    private SellerMapper sellerMapper;

    @Override
    public SellerDTO save(SellerDTO sellerDTO) {
        Seller seller = sellerMapper.toSeller(sellerDTO);
        return sellerMapper.toSellerDto(sellerCrudRepository.save(seller));
    }

    @Override
    public SellerDTO update(SellerDTO sellerDTO) {

        Seller seller = sellerMapper.toSeller(sellerDTO);
        return sellerMapper.toSellerDto(sellerCrudRepository.save(seller));
    }

    @Override
    public void delete(long sellerId) {
       sellerCrudRepository.deleteById(sellerId);
    }

    @Override
    public Optional<SellerDTO> findById(long sellerId) {

        return sellerCrudRepository.findById(sellerId)
                .map(seller -> sellerMapper.toSellerDto(seller));
    }

    @Override
    public Optional<List<SellerDTO>> getAllActive() {
        return sellerCrudRepository.findByStateEquals(SellerUtils.ACTIVE)
                .map(sellerList -> sellerMapper.toListSellerDtos(sellerList));
    }

    @Override
    public List<SellerDTO> getAll() {
          List<Seller> sellers = (List<Seller>) sellerCrudRepository.findAll();
          return sellerMapper.toListSellerDtos(sellers);
    }
}
