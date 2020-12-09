package ar.com.plug.examen.domain.utils;

public class FieldValidator {

	public static String validateRequired(String name,Object value) {
		String result = null;
		if(value == null || value.equals(""))
			result = "El campo "+ name +" es obligatorio";
	
		return result; 
	}
}
