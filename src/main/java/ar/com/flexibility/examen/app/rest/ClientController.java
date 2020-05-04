package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.dto.CartItemDto;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.model.ShoppingCart;
import ar.com.flexibility.examen.domain.service.ClientService;
import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.domain.service.ShoppingCartService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {

    private ClientService clientService;
    private ShoppingCartService shoppingCartService;
    private ProductService productService;

    @Autowired
    public ClientController(ClientService clientService,
                            ShoppingCartService shoppingCartService,
                            ProductService productService) {
        this.clientService = clientService;
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully.")
    })
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.retrieveClients();

        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully."),
            @ApiResponse(code = 404, message = "Could not retrieve the resource.")
    })
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) {
        Client client = clientService.retrieveClientById(id);

        if (client == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(client, HttpStatus.OK);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully."),
            @ApiResponse(code = 400, message = "Could not create the resource.")
    })
    public ResponseEntity<Client> createClient(@Valid @NotNull @RequestBody Client client) {
        Client newClient = clientService.addClient(client);

        if (newClient == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(newClient, HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource was created successfully."),
            @ApiResponse(code = 400, message = "Could not update the resource.")
    })
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long id,
                                               @Valid @NotNull @RequestBody Client client) {
        Client updatedClient = clientService.updateClient(id, client);

        if (updatedClient == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(updatedClient, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully."),
            @ApiResponse(code = 500, message = "Could not delete the resource.")
    })
    public ResponseEntity deleteClient(@PathVariable("id") Long id) {
        boolean deletedClient = clientService.deleteClient(id);

        if (deletedClient) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "{id}/cart")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully."),
            @ApiResponse(code = 400, message = "Could not create the resource.")
    })
    public ResponseEntity<ShoppingCart> getClientCart(@PathVariable("id") Long id) {
        Client client = clientService.retrieveClientById(id);

        if (client == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        ShoppingCart shoppingCart = shoppingCartService.retrieveOpenCartForClient(client);

        return new ResponseEntity(shoppingCart, HttpStatus.OK);
    }

    @PatchMapping(path = "{id}/cart")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully."),
            @ApiResponse(code = 400, message = "Could not create the resource."),
            @ApiResponse(code = 404, message = "Could not retrieve parent the resource."),
            @ApiResponse(code = 500, message = "Could not add the resource."),
    })
    public ResponseEntity<String> addProductToCart(@PathVariable("id") Long id,
                                                   @Valid @NotNull @RequestBody CartItemDto cartItemDto) {
        Client client = clientService.retrieveClientById(id);

        if (client == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Product product = productService.retrieveProductById(cartItemDto.getProductId());

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean productAdded = shoppingCartService.addProductToCart(client,
                product, cartItemDto.getQuantity());

        if (!productAdded) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping(path = "{id}/cart")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request was completed successfully."),
            @ApiResponse(code = 404, message = "Could not retrieve parent the resource."),
            @ApiResponse(code = 500, message = "Could not add the resource."),
    })
    public ResponseEntity<Long> processCart(@PathVariable("id") Long id) {
        Client client = clientService.retrieveClientById(id);

        if (client == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Long orderId = shoppingCartService.processCart(client);

        if (orderId == 0) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }
}
