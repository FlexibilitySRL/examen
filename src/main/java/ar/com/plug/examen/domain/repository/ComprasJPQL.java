package ar.com.plug.examen.domain.repository;

public class ComprasJPQL {
	
	public static final String findBuyByIdCliente =
			"SELECT new ar.com.plug.examen.domain.dto.GetComprasByClienteDTO("
			+ "p.producto) FROM Compras AS c "
			+ "INNER JOIN Productos AS p ON c.productos = p.id "
			+ "INNER JOIN Clientes AS t ON c.clientes = t.id "
			+ "WHERE (t.id = :clientes)";
}
