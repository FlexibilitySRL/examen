package ar.com.plug.examen.app.fixtures;

import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.domain.model.Seller;
import java.util.ArrayList;
import java.util.List;


public class SellerFixture {

    public static SellerApi getSellerApiWithId(Long id) {

        SellerApi sellerApi = SellerApi.builder()
                .id(id)
                .build();

        return sellerApi;
    }

    public static SellerApi getSellerApi() {

        SellerApi sellerApi = SellerApi.builder()
                .userName("lrobles")
                .firstName("Lucas")
                .lastName("Robles")
                .age(36L)
                .email("lrobles@gmail.com")
                .build();

        return sellerApi;
    }

    public static List<SellerApi> getSellerApitList(SellerApi sellerApi1, SellerApi sellerApi2) {

        List<SellerApi> lsSellers = new ArrayList<>();
        lsSellers.add(sellerApi1);
        lsSellers.add(sellerApi2);
        return lsSellers;
    }

    public static Seller getSellerWithId(Long id) {

        Seller seller = Seller.builder()
                .id(id)
                .build();

        return seller;

    }

    public static Seller getSeller() {

        Seller seller = Seller.builder()
                .userName("lrobles")
                .firstName("Lucas")
                .lastName("Robles")
                .age(36L)
                .email("lrobles@gmail.com")
                .build();

        return seller;
    }
}
