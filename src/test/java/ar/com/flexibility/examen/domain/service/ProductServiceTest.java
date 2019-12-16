package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.Application;
import ar.com.flexibility.examen.domain.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    public void createNewProduct() {
        Product newProduct = this.createProduct("Pintura 5L", "Latext para interior color blanco. Contenido 5L.");
        Product savedProduct = productService.save(newProduct);

        Assert.assertNotNull("El id no deberia ser nulo", savedProduct.getId());
        Assert.assertSame("El name deberia ser 'Pintura 5L'", "Pintura 5L", savedProduct.getName());
        Assert.assertSame("El description deberia ser 'Latext para interior color blanco. Contenido 5L.'", "Latext para interior color blanco. Contenido 5L.", savedProduct.getDescription());
    }

    @Test
    public void updateProduct() {
        Product newProduct = this.createProduct("Taladrorrrr", "Taladro percutor con varios accesorios.");
        Product savedProduct = productService.save(newProduct);

        Assert.assertNotNull("El id no deberia ser nulo", savedProduct.getId());

        savedProduct.setName("Taladro");
        Product savedProduct2 = productService.save(savedProduct);

        Assert.assertNotNull("El id no deberia ser nulo", savedProduct2.getId());
        Assert.assertSame("El name deberia ser 'Taladro'", "Taladro", savedProduct2.getName());
    }

    @Test
    public void findProduct() {
        Product newProduct = this.createProduct("Caja de destornilladores", "Una completa caja de destornilladores con la mas completa variedad");
        Product savedProduct = productService.save(newProduct);

        Assert.assertNotNull("El id no deberia ser nulo", savedProduct.getId());

        Product findedProduct = productService.find(savedProduct.getId());

        Assert.assertSame("El name deberia ser 'Caja de destornilladores'", "Caja de destornilladores", findedProduct.getName());
    }

    private Product createProduct(String name, String description) {
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setDescription(description);
        return newProduct;
    }
}
