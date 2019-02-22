package ar.com.flexibility.examen.app.api;

import ar.com.flexibility.examen.domain.exception.ExceptionSystem;


public class ExceptionApi {
	public String msg;
	
	public ExceptionApi(ExceptionSystem exception) {
		this.msg = exception.getMsg();
	}
}
