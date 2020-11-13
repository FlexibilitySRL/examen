package ar.com.plug.examen.domain.model;

import ar.com.plug.examen.domain.exceptions.EmptyBrandException;
import ar.com.plug.examen.domain.exceptions.EmptyNameException;
import ar.com.plug.examen.domain.exceptions.InvalidPriceException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductTest {

    Product aProduct;

    @Test
    public void productWithEmptyNameShouldRaiseEmptyNameException(){
        assertThrows(EmptyNameException.class, ()-> {
            aProduct = new Product("", 125.5, "Coca-Cola");
        });
    }

    @Test
    public void productWithZeroPriceShouldRaiseInvalidPriceException(){
        assertThrows(InvalidPriceException.class, ()-> {
            aProduct = new Product("Coca", 0.0, "Coca-Cola");
        });
    }

    @Test
    public void productWithLessThanZeroPriceShouldRaiseInvalidPriceException(){
        assertThrows(InvalidPriceException.class, ()-> {
            aProduct = new Product("Coca", -0.1, "Coca-Cola");
        });
    }

    @Test
    public void productWithEmptyBrandShouldRaiseEmptyBrandException(){
        assertThrows(EmptyBrandException.class, ()-> {
            aProduct = new Product("Coca", 125.5, "");
        });
    }

    @Test
    public void creationalTestProduct() throws EmptyBrandException, EmptyNameException, InvalidPriceException {
        aProduct = new Product("Coca", 125.5, "Coca-Cola");
        assertEquals(aProduct.getName(), "Coca");
        assertEquals(aProduct.getPrice(), 125.5);
        assertEquals(aProduct.getBrand(), "Coca-Cola");
    }

    @Test
    public void givenTwoProductsWithExacltySameValuesWhenComparedThemByEqualsReturnsTrue() throws EmptyBrandException, EmptyNameException, InvalidPriceException {
        aProduct = new Product("Coca", 125.5, "Coca-Cola");
        Product anotherProduct = new Product("Coca", 125.5, "Coca-Cola");

        assertEquals(aProduct, anotherProduct);
    }

    @Test
    public void givenTwoProductsWithDifferentNameWhenComparedThemByEqualsReturnsFalse() throws EmptyBrandException, EmptyNameException, InvalidPriceException {
        aProduct = new Product("Coca", 125.5, "Coca-Cola");
        Product anotherProduct = new Product("Coca Light", 125.5, "Coca-Cola");

        assertNotEquals(aProduct, anotherProduct);
    }

    @Test
    public void givenTwoProductsWithDifferentPriceWhenComparedThemByEqualsReturnsFalse() throws EmptyBrandException, EmptyNameException, InvalidPriceException {
        aProduct = new Product("Coca", 125.5, "Coca-Cola");
        Product anotherProduct = new Product("Coca", 85.5, "Coca-Cola");

        assertNotEquals(aProduct, anotherProduct);
    }

    @Test
    public void givenTwoProductsWithDifferentBrandWhenComparedThemByEqualsReturnsFalse() throws EmptyBrandException, EmptyNameException, InvalidPriceException {
        aProduct = new Product("Coca", 125.5, "Coca-Cola");
        Product anotherProduct = new Product("Coca", 85.5, "Coke");

        assertNotEquals(aProduct, anotherProduct);
    }
}
