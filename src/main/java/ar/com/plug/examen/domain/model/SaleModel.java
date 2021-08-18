package ar.com.plug.examen.domain.model;

import ar.com.plug.examen.domain.dto.Customer;
import ar.com.plug.examen.domain.dto.ItemByLine;
import ar.com.plug.examen.domain.dto.PaymentType;
import ar.com.plug.examen.domain.dto.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleModel {

    private Long id;
    private Customer customer;
    private Seller seller;
    private String dateTrx;
    private Double subTotal;
    private Double tax;
    private Double total;
    private Integer idStatus;
    private PaymentType paymentType;
    private List<ItemByLine> items;

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", Customer='" + customer + '\'' +
                ", Seller='" + seller + '\'' +
                ", dateTrx='" + dateTrx + '\'' +
                ", sub_total='" + subTotal + '\'' +
                ", total='" + total + '\'' +
                ", tax='" + tax + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", items='" + items + '\'' +
                ", idStatus='" + idStatus + '\'' +
                '}';
    }
}
