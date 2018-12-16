package com.ddvitos.app.comexportbackend.exceptions;

public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8095718335678171898L;

	public RecordNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
