package ar.com.plug.examen.domain.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTypeModel {

    private Long id;
    private String currency;
    private String type;
    private Double availableBalance;
    private Integer idStatus;

    @Override
    public String toString() {
        return "PaymentType{" +
                "id=" + id +
                ", currency ='" + currency + '\'' +
                ", type='" + type + '\'' +
                ", availableBalance='" + availableBalance + '\'' +
                ", idStatus='" + idStatus + '\'' +
                '}';
    }
}
