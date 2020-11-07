package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.dao.ProductDAO;
import ar.com.plug.examen.model.Products;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductsTest {

    @Autowired
    private ProductDAO productDAO;

    @Test
    public void testCreateProduct(){
        productDAO.save(new Products("Camiseta","Millonarios xl",12345678910L,12345678910L,true));
        Products savedProduct = productDAO.findByName("Camiseta");
        assert(savedProduct.getName()).equals("Camiseta");
    }
    @Test
    public void testNoExistProduct(){
        productDAO.save(new Products("Camiseta","Millonarios xl",12345678910L,12345678910L,true));
        Products savedProduct = productDAO.findByName("Camiseta Nueva");
        Assert.assertNull(savedProduct);
    }

    @Test
    public void testListProduct(){
        List<Products> products = productDAO.findAll();
       /* for (Products products1 : products){
            System.out.println(products1);
        }*/
        Assert.assertTrue(products.size()>0);
    }

    @Test
    public void testDeleteProduct(){
        Long id = 1L;
        Optional<Products> isExistBeforeDelete = productDAO.findById(id);
        productDAO.deleteById(id);
        Optional<Products> isExistAfterDelete = productDAO.findById(id);
        Assert.assertTrue(isExistBeforeDelete.isPresent());
        Assert.assertFalse(isExistAfterDelete.isPresent());

    }


}
