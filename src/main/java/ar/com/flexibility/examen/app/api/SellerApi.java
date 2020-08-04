package ar.com.flexibility.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "seller")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SellerApi extends PersonApi {

}
