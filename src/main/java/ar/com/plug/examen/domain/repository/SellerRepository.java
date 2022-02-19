package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.model.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long>
{
	Page<Seller> findAllByActiveTrue(Pageable pageable);
	Seller findByCode(String code);
}
