package ar.com.plug.examen.domain.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.plug.examen.domain.model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

	@Query(value = "SELECT s FROM Seller s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<Seller> findByName(@Param("name") String name);

}
