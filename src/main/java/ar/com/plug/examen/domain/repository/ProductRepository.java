package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
     /**
      * Query that find a row with conditions of category and descriptionProduct
      * @param category it is big category of product
      * @param descriptionProduct it is a name of product
      * @return object of product if exist in database
      */
     Product findProductByCategoryAndDescriptionProduct(String category, String descriptionProduct);
}
