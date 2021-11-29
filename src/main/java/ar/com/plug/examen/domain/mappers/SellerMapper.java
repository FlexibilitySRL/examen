package ar.com.plug.examen.domain.mappers;

import ar.com.plug.examen.app.dto.SellerDto;
import ar.com.plug.examen.domain.model.Seller;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;


@Component
public class SellerMapper {

    public SellerDto sellerToSellerApi(Seller seller) {

        SellerDto sellerApi = SellerDto
                .builder()
                .id(seller.getId())
                .userName(seller.getUserName())
                .firstName(seller.getFirstName())
                .lastName(seller.getLastName())
                .age(seller.getAge())
                .email(seller.getEmail())
                .build();

        return sellerApi;
    }

    public Seller sellerApiToSeller(SellerDto sellerApi) {

        Seller seller = Seller
                .builder()
                .id(sellerApi.getId())
                .userName(sellerApi.getUserName())
                .firstName(sellerApi.getFirstName())
                .lastName(sellerApi.getLastName())
                .age(sellerApi.getAge())
                .email(sellerApi.getEmail())
                .build();

        return seller;
    }

    public List<SellerDto> sellersToListSellerApi(List<Seller> sellers) {
        return sellers
                .stream()
                .map(this::sellerToSellerApi)
                .collect(Collectors.toList());
    }
}
