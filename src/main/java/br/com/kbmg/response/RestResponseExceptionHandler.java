package br.com.kbmg.response;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({ AccessDeniedException.class })
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

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<ObjectResponse> handleBadRequestConstraintViolation(final ConstraintViolationException ex,
			final WebRequest request) {
		ObjectResponse response = new ObjectResponse();
		
		ex.getConstraintViolations().forEach(e -> response.getErrors().add(e.getPropertyPath() + ": " + e.getMessage()));
		return new ResponseEntity<ObjectResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { EntityNotFoundException.class })
	protected ResponseEntity<ErrorResponse> handleNotFound(final RuntimeException ex, final WebRequest request) {
		return generatedError(ex.getMessage(), HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
	}

	@ExceptionHandler({ DataAccessException.class })
	protected ResponseEntity<ErrorResponse> handleConflict(final RuntimeException ex, final WebRequest request) {
		return generatedError(ex.getMessage(), HttpStatus.CONFLICT, HttpStatus.CONFLICT.value());
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ErrorResponse> handleInternal(final RuntimeException ex, final WebRequest request) {

		return generatedError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
				HttpStatus.INTERNAL_SERVER_ERROR.value());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, String> errors = new HashMap<String, String>();
		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		response.put("message", errors);
		response.put("errorCode", HttpStatus.BAD_REQUEST.value());

		return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String message = "Não foi possível ler os dados.";
		Throwable mostSpecificCause = ex.getMostSpecificCause();
		
		if (mostSpecificCause != null) {
			message = mostSpecificCause.getMessage();

			String nomeDaClasse = mostSpecificCause.getLocalizedMessage();
			int inicio = nomeDaClasse.lastIndexOf(".") + 1;
			int fim = nomeDaClasse.length() - 1;

			message = nomeDaClasse.substring(inicio, fim) + " com valor inválido.";
		} 
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", message.replaceAll("\"", ""));
		response.put("errorCode", HttpStatus.BAD_REQUEST.value());

		return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
	}

	private ResponseEntity<ErrorResponse> generatedError(String message, HttpStatus http, int httpStatusValue) {
		ErrorResponse error = new ErrorResponse();

		error.setErrorCode(httpStatusValue);
		error.setMessage(message);
		return new ResponseEntity<ErrorResponse>(error, http);
	}

}
