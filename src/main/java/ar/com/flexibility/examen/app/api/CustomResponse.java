package ar.com.flexibility.examen.app.api;

public class CustomResponse {
	
	private boolean error;
	private boolean success;
	private String menssage;
	
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMenssage() {
		return menssage;
	}
	public void setMensaje(String menssage) {
		this.menssage = menssage;
	}
	
	
}
