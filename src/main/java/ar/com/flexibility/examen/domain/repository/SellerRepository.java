package ar.com.flexibility.examen.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.com.flexibility.examen.domain.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {

	@Query ("select COUNT(*) from Sale p where p.seller.identifier=:identifier")
	int countSalesByIdentifier(@Param("identifier") String identifier);

	@Modifying
	@Query ("delete from Seller p where p.identifier=:identifier")
	int deleteByIdentifier(@Param("identifier") String identifier);

	Seller getFirstByIdentifier(String identifier);

}
