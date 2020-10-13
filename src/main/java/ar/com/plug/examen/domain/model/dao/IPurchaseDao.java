package ar.com.plug.examen.domain.model.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ar.com.plug.examen.domain.enums.PurchaseStatus;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Purchase;
import ar.com.plug.examen.domain.model.Seller;

//CrudRepository
public interface IPurchaseDao extends CrudRepository<Purchase, Long> {
	
	@Transactional
	@Modifying
	@Query("update Purchase p set p.status = ?2 where p.client.id = ?1 and p.status = 'PENDING' ")
	public void bulkChangeStatus(Long clientID, PurchaseStatus status);
	
	public List<Purchase> findByClientAndStatus(Client id, PurchaseStatus status);
	
	public List<Purchase> findBySellerAndStatus(Seller id, PurchaseStatus status);

	public List<Purchase> findByClient(Client client);

	@Query("select p from Purchase p join p.items i join i.product prod where prod = ?1")
	public List<Purchase> findByProduct(Product product);
	
	
}
