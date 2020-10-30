package ar.com.plug.examen.app.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import ar.com.plug.generated.api.VendorsApi;

import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-10-29T10:08:36.293-03:00[America/Argentina/Buenos_Aires]")

@Controller
@RequestMapping("${openapi.paymentManagement.base-path:}")
public class VendorsApiController implements VendorsApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public VendorsApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
