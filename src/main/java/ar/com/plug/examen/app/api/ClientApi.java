package ar.com.plug.examen.app.api;

import lombok.Data;

@Data
public class ClientApi {

  private Long id;
  private String userName;
  private String firstName;
  private String lastName;
  private Long age;
  private String email;

}
