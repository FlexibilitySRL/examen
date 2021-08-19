package ar.com.plug.examen.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SellerModel {

    private Long id;
    private String name;
    private Integer idStatus;

    @Override
    public String toString() {
        return "Seller{" +
                "idSeller=" + id+
                ", name='" + name + '\'' +
                ", status='" + idStatus + '\'' +
                '}';
    }
}
