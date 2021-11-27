package ar.com.plug.examen.app.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerApi {
    
  private Long id;
  private String userName;
  private String firstName;
  private String lastName;
  private Long age;
  private String email;
    
}
