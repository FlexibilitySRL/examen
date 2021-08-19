package ar.com.plug.examen.domain.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerModel {
    private Long id;
    private String name;
    private Integer idStatus;

    @Override
    public String toString() {
        return "CustomerModel{" +
                "id_customer=" + id +
                ", name='" + name + '\'' +
                ", id_status='" + idStatus + '\'' + "}";
    }
}
