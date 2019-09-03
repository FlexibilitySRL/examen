package ar.com.flexibility.examen.domain.base;

import org.springframework.http.HttpStatus;

public class BaseResponse<T> {
	private HttpStatus statusCode;
	private T data;

	public BaseResponse(HttpStatus statusCode, T data) {
		this.statusCode = statusCode;
		this.data = data;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
