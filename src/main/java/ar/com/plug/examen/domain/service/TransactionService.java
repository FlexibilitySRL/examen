package ar.com.plug.examen.domain.service;

import org.springframework.stereotype.Repository;

import ar.com.plug.examen.app.api.PurchaseApprovalApi;
import ar.com.plug.examen.app.api.TransactionApi;
import ar.com.plug.examen.domain.exception.NotExistException;
import ar.com.plug.examen.domain.exception.ValidatorException;
import ar.com.plug.examen.domain.model.Transaction;

/**
 * Esta interfaz se encarga de proveer el servicio para la creacion y busqueda de transacciones
 * @author oscar
 *
 */
@Repository
public interface TransactionService {

	/**
	 * Este metodo devuelve una transaccion, en caso de que el id no existe en la base devuelve una excepcion.
	 * @param id es la transaccion a buscar en la base
	 * @return devuelve el objeto Transaccion
	 * @throws NotExistException En caso de que no existe el id
	 */
	public TransactionApi find(Long id)throws NotExistException;
	/**
	 * Este metodo se encarga de crear una transaccion, para eso valida previamente el objeto recibido. Luego de validarlo
	 * mapea el objeto PurchaseApprovalApi a un objeto Transaction
	 * @param transaction
	 * @throws ValidatorException Excepcion que se arroja si no pasa las validaciones el objeto PurchaseApprovalApi
	 * @throws NotExistException Excepcion que se arroja si los datos pasados no existen en la base por ejemplo id de cliente
	 */
	public Long create(PurchaseApprovalApi transaction) throws ValidatorException,NotExistException;
}
