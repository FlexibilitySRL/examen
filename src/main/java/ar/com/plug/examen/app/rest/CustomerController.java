package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.config.SwaggerResponseOk;
import ar.com.plug.examen.domain.dto.CustomerDTO;
import ar.com.plug.examen.domain.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/saveCustomer", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)

    @ApiOperation(value = "guarda un registro de un producto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se agregan los valores correctamente",
                    response = SwaggerResponseOk.class),
            @ApiResponse(code = 400, message = "En la adiccion de los datos",
                    response = SwaggerResponseOk.class),
    })
    public void createCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.createCustomer(customerDTO);
    }

    @DeleteMapping(path = "/removeCustomer/{idCustomer}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCustomer(@PathVariable("idCustomer") Long id) {
        customerService.deleteCustomer(id);
    }

    @PostMapping(path = "/editCustomer/{idCustomer}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public void modifyCustomer(@PathVariable("idCustomer") Long id, @RequestBody CustomerDTO customerDTO) {
        customerService.editCustomer(id, customerDTO);
    }

}
