package ar.com.flexibility.examen.app.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "shoppingList")
public class ShoppingListApi {
    @JsonProperty
    private String shoppingListId;
    @JsonProperty
    private String productId;

    public String getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(String shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
