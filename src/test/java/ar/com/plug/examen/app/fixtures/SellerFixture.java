package ar.com.plug.examen.app.fixtures;

import ar.com.plug.examen.app.dto.SellerDto;
import ar.com.plug.examen.domain.model.Seller;
import java.util.ArrayList;
import java.util.List;


public class SellerFixture {

    public static SellerDto getSellerApiWithId(Long id) {

        SellerDto sellerApi = SellerDto.builder()
                .id(id)
                .build();

        return sellerApi;
    }

    public static SellerDto getSellerApi() {

        SellerDto sellerApi = SellerDto.builder()
                .userName("lrobles")
                .firstName("Lucas")
                .lastName("Robles")
                .age(36L)
                .email("lrobles@gmail.com")
                .build();

        return sellerApi;
    }

    public static List<SellerDto> getSellerApitList(SellerDto sellerApi1, SellerDto sellerApi2) {

        List<SellerDto> lsSellers = new ArrayList<>();
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
