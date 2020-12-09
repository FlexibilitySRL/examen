package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.exception.NotExistException;
import ar.com.plug.examen.domain.exception.ValidatorException;
/**
 * Este interfaz provee un crud para que cada implementacion 
 * la implemente segun sus objetos
 * @author oscar
 *
 * @param <T> Es el objeto de entrada 
 * @param <S> Es el objeto que se va a guardar en la base de datos.
 */
public interface ServiceGeneric <T,S>{
	/**
	 * Este metodo crea un objeto en la objeto en la base de datos, previamente se valida 
	 * el objeto de entrada en caso de que no pase la validacion se arroja una excepcion.
	 * Luego de la validacion se hace el mapeo desde (T) a (S)
	 * @param entity Objeto de entrada para poder dar de alta
	 * @throws ValidatorException esta excepcion se arroja si el objeto de entrada no pasa las validaciones.
	 */
	public void create(T entity)throws ValidatorException;
	/**
	 * Este metodo modifica un objeto en la base de datos, al igual que en el alta se valida y luego se hace el mapeo.
	 * desde (T) a (S).
	 * @param entity Objeto de entrada para poder modificar.
	 * @throws ValidatorException Esta excepcion se arroja si el objeto de entrada no pasa las validaciones.
	 */
	public void update(T entity)throws ValidatorException,NotExistException;
	
	public void delete(Long id) throws NotExistException, ValidatorException;
	/**
	 * Este metodo se encarga de devolver un objeto dependiendo del id, este es validado igual que en el alta y modificacion
	 * @param id se utiliza para obtener el objeto.
	 * @return
	 * @throws NotExistException Esta excepcion se arroja si el id proporcionado no se encuentra en la base.
	 * @throws ValidatorException Esta excepcion se arroja si el objeto de entrada no pasa las validaciones.
	 */
	public T find(Long id) throws NotExistException, ValidatorException;
	/**
	 * Este metodo se encarga de obtener todos los objetos que se encuentran en la base
	 * @return
	 * @throws NotExistException
	 */
	public List<T> findAll() throws NotExistException;

}
