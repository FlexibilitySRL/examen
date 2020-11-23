package ar.com.plug.examen.domain.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import ar.com.plug.examen.domain.model.Producto;

public interface ProductoService extends JpaRepository<Producto, Long>, QueryByExampleExecutor<Producto> {

	/**
	 * Metodo que permite consultar un producto por medio del codigo
	 * @param codProducto
	 * @return
	 */
	Producto findByCodProducto(String codProducto);
}
