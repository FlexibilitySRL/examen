package ar.com.flexibility.examen.utils;
import ar.com.flexibility.examen.app.api.CustomResponse;

public class Utils {
	
	public static CustomResponse customResponse(String mensaje, boolean success, boolean error) {
		CustomResponse crb = new CustomResponse();
		crb.setError(error);
		crb.setSuccess(success);
		crb.setMensaje(mensaje);
		return crb;
	}

	
}
