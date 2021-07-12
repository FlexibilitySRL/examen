package ar.com.plug.examen.domain.mappers;

import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.domain.model.Seller;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class SellerMapper {

  public Seller sellerApiToSeller(SellerApi sellerApi) {
    Seller seller = new Seller();
    seller.setId(sellerApi.getId());
    seller.setUserName(sellerApi.getUserName());
    seller.setFirstName(sellerApi.getFirstName());
    seller.setLastName(sellerApi.getLastName());
    seller.setAge(sellerApi.getAge());
    seller.setEmail(sellerApi.getEmail());
    return seller;
  }

  public SellerApi sellerToSelerApi(Seller seller) {
    SellerApi sellerApi = new SellerApi();
    sellerApi.setId(seller.getId());
    sellerApi.setUserName(seller.getUserName());
    sellerApi.setFirstName(seller.getFirstName());
    sellerApi.setLastName(seller.getLastName());
    sellerApi.setAge(seller.getAge());
    sellerApi.setEmail(seller.getEmail());
    return sellerApi;
  }

  public List<SellerApi> sellersToListSellerApi(List<Seller> sellers) {
    return sellers.stream().map(this::sellerToSelerApi).collect(Collectors.toList());
  }
}
