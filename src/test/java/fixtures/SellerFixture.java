package fixtures;

import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.domain.model.Seller;
import java.util.ArrayList;
import java.util.List;

public class SellerFixture {

  public static SellerApi getSellerApiWithId(Long id) {
    SellerApi sellerApi = new SellerApi();
    sellerApi.setId(id);
    return sellerApi;
  }

  public static SellerApi getSellerApi() {
    SellerApi sellerApi = new SellerApi();
    sellerApi.setUserName("hgomez");
    sellerApi.setFirstName("Hernan");
    sellerApi.setLastName("Gomez");
    sellerApi.setAge(22L);
    sellerApi.setEmail("hgomez@hexacta.com");
    return sellerApi;
  }

  public static List<SellerApi> getSellerApitList(SellerApi sellerApi1, SellerApi sellerApi2) {
    List<SellerApi> lsSellers = new ArrayList<SellerApi>();
    lsSellers.add(sellerApi1);
    lsSellers.add(sellerApi2);
    return lsSellers;
  }

  public static Seller getSellerWithId(Long id) {
    Seller seller = new Seller();
    seller.setId(id);
    return seller;
  }

  public static Seller getSeller() {
    Seller seller = new Seller();
    seller.setUserName("hgomez");
    seller.setFirstName("Hernan");
    seller.setLastName("Gomez");
    seller.setAge(22L);
    seller.setEmail("hgomez@hexacta.com");
    return seller;
  }

}
