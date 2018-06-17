package com.arthurbarbosa.cursomc.services.exceptions;

public class ObjectNotFounException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFounException(String msg) {
		super(msg);
	}
	
	public ObjectNotFounException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
