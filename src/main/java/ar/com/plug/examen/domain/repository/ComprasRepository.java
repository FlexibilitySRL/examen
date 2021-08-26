package ar.com.plug.examen.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.com.plug.examen.domain.dto.GetComprasByClienteDTO;
import ar.com.plug.examen.domain.model.Compras;

public interface ComprasRepository extends JpaRepository<Compras, Integer>{
	
	@Query(ComprasJPQL.findBuyByIdCliente)
	public List<GetComprasByClienteDTO> findByIDCliente(
			@Param (value = "clientes") Integer idcliente);

}
