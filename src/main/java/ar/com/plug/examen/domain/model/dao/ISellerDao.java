package ar.com.plug.examen.domain.model.dao;

import org.springframework.data.repository.CrudRepository;

import ar.com.plug.examen.domain.model.Seller;

public interface ISellerDao extends CrudRepository<Seller, Long> {
	
	//Aca se pueden agregar las consultas personlizadas pero las default ya estan creadas
	
//	@Query("select c from Cliente c left join fetch c.facturas f where c.id = ?1")
//	public Client fetchByIdWithFacturas(Long id);
	
	
	
}
