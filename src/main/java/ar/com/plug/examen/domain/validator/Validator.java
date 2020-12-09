package ar.com.plug.examen.domain.validator;

/**
 * Esta intefaz se encarga de validar campos devolviendo el error en caso de que lo haya
 * @author oscar
 *
 * @param <T>
 */
public interface Validator <T>{

	public String validate(T value);
}
