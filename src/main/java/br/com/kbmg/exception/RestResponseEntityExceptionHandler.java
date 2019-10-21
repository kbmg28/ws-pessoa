package br.com.kbmg.exception;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler({ AccessDeniedException.class})
	public ResponseEntity<ErrorResponse> handleAccessDeniedException(final Exception ex, final WebRequest request) {
		return generatedError(ex.getMessage(), HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.value());
	}

	@ExceptionHandler({ SQLException.class })
	public ResponseEntity<ErrorResponse> handleSQL(final Exception ex, final WebRequest request) {
		return generatedError("Erro ao conectar com o banco", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
	}

	@ExceptionHandler({ IllegalArgumentException.class })
	public ResponseEntity<ErrorResponse> handleArguments(final Exception ex, final WebRequest request) {
		return generatedError(ex.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
	}
	
	@ExceptionHandler(value = { EntityNotFoundException.class })
	protected ResponseEntity<ErrorResponse> handleNotFound(final RuntimeException ex, final WebRequest request) {
		return generatedError(ex.getMessage(), HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
	}

	@ExceptionHandler({ DataAccessException.class })
	protected ResponseEntity<ErrorResponse> handleConflict(final RuntimeException ex, final WebRequest request) {
		return generatedError(ex.getMessage(), HttpStatus.CONFLICT, HttpStatus.CONFLICT.value());
	}

	@ExceptionHandler({ Exception.class})
	public ResponseEntity<ErrorResponse> handleInternal(final RuntimeException ex, final WebRequest request) {
		
		return generatedError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
				HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
	private ResponseEntity<ErrorResponse> generatedError(String message, HttpStatus http, int httpStatusValue) {
		ErrorResponse error = new ErrorResponse();
		
		error.setErrorCode(httpStatusValue);
		error.setMessage(message);
		return new ResponseEntity<ErrorResponse>(error, http);
	}

}
