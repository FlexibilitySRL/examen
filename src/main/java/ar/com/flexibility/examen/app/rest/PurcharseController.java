package ar.com.flexibility.examen.app.rest;

import ar.com.flexibility.examen.app.api.PurcharseApi;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Purcharse;
import ar.com.flexibility.examen.domain.model.ShoppingList;
import ar.com.flexibility.examen.domain.service.impl.ClientServiceImpl;
import ar.com.flexibility.examen.domain.service.impl.PurcharseServiceImpl;
import ar.com.flexibility.examen.domain.service.impl.ShoppingListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purcharse")
public class PurcharseController {

    @Autowired
    private ClientServiceImpl clientService;
    @Autowired
    private ShoppingListServiceImpl shoppingListService;
    @Autowired
    private PurcharseServiceImpl purcharseService;

    @GetMapping
    public List<Purcharse> showAllPurcharses(){
        return purcharseService.findAll();
    }

    @PostMapping("/buy")
    public void addPurcharse(@RequestBody PurcharseApi purcharseApi) {
        ShoppingList searchedShoppingList = shoppingListService.findById(Long.parseLong(purcharseApi.getShoppingListId()));
        Client searchedClient = clientService.findById(Long.parseLong(purcharseApi.getClientId()));
        Purcharse purcharse = new Purcharse();

        Purcharse buy = purcharse.buy(searchedShoppingList, searchedClient);

        purcharseService.addPurcharse(buy);
    }

    @PutMapping
    public void updatePurcharse(@RequestBody Purcharse purcharse) {
        purcharseService.updatePurcharse(purcharse);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePurcharse(@PathVariable Long id) {
        purcharseService.deletePurcharse(id);
    }


}
