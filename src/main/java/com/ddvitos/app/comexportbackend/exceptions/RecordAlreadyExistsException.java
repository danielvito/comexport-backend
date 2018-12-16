package com.ddvitos.app.comexportbackend.exceptions;

public class RecordAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -8095718335678171898L;

	public RecordAlreadyExistsException(String errorMessage) {
		super(errorMessage);
	}

}
