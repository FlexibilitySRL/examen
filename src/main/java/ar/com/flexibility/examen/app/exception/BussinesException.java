package ar.com.flexibility.examen.app.exception;

public class BussinesException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String[] messages;

	public BussinesException(String... errorMessage) {
		this.messages = errorMessage;
	}

	public String[] getMessages() {
		return messages;
	}

	public void setMessages(String[] messages) {
		this.messages = messages;
	}

}
