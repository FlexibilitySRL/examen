package ar.com.flexibility.examen.domain.exception;

public abstract class ExceptionGeneric extends Exception {

	private static final long serialVersionUID = 7766681825318222454L;
	private String msg;

	public ExceptionGeneric(String msg) {
		super();
		this.setMsg(msg);
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
}