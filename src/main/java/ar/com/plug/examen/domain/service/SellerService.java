package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Seller;

import java.util.List;

public interface SellerService {
    public List<Seller> listAllSeller();
    public Seller getSeller(Long id);
    public Seller createSeller(Seller seller);
    public Seller updateSeller(Seller seller);
    public Seller deleteSeller(Long id);
}