package ar.com.plug.examen.objects;

import ar.com.plug.examen.domain.dto.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCustomer {
    private Product product;
    private Integer quantityRequired;
    private StatusTransaction statusTransaction;

    @Override
    public String toString() {
        return "RequestCustomer{" +
                "product=" + product.toString() +
                ", quantityRequired='" + quantityRequired + '\'' +
                ", statusTrx='" + statusTransaction + '\'' +
                '}';
    }
}
