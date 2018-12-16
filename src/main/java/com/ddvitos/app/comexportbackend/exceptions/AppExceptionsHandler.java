package com.ddvitos.app.comexportbackend.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.ddvitos.app.comexportbackend.ui.model.response.ErrorMessage;

@ControllerAdvice
public class AppExceptionsHandler {

	private ErrorMessage errorMessage(Exception ex) {
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
		return errorMessage;
	}

	@ExceptionHandler(value = { ContaContabilServiceException.class })
	public ResponseEntity<Object> handleContaContabilServiceException(ContaContabilServiceException ex,
			WebRequest request) {
		return new ResponseEntity<>(errorMessage(ex), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { LancamentoContabilServiceException.class })
	public ResponseEntity<Object> handleLancamentoContabilServiceException(LancamentoContabilServiceException ex,
			WebRequest request) {
		return new ResponseEntity<>(errorMessage(ex), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { RecordNotFoundException.class })
	public ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {
		return new ResponseEntity<>(errorMessage(ex), new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { RecordAlreadyExistsException.class })
	public ResponseEntity<Object> handleRecordAlreadyExistsException(RecordAlreadyExistsException ex,
			WebRequest request) {
		return new ResponseEntity<>(errorMessage(ex), new HttpHeaders(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(value = { HttpMessageNotReadableException.class, MissingServletRequestParameterException.class })
	public ResponseEntity<Object> handleBadRequestException(Exception ex, WebRequest request) {
		return new ResponseEntity<>(errorMessage(ex), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
		return new ResponseEntity<>(errorMessage(ex), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
