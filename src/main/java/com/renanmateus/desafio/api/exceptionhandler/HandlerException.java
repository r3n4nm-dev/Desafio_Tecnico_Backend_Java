package com.renanmateus.desafio.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.renanmateus.desafio.domain.exception.EntidadeJaExistenteException;
import com.renanmateus.desafio.domain.exception.EntidadeNaoEncontradaException;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

	private static final String MSG_ERRO_VALIDACAO = "Alguns campos não foram informados corretamente.";

	@ExceptionHandler(EntidadeJaExistenteException.class)
	public ResponseEntity<Object> handleEntidadeJaExistenteException(EntidadeJaExistenteException ex,
			WebRequest request) {
		return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex,
			WebRequest request) {
		return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();
		List<Campo> campos = bindingResult.getFieldErrors().stream()
				.map(fieldError -> new Campo(fieldError.getField(), fieldError.getDefaultMessage()))
				.collect(Collectors.toList());
		Error error = new Error(status.value(), MSG_ERRO_VALIDACAO, LocalDateTime.now(), campos);
		return super.handleExceptionInternal(ex, error, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (body == null) {
			body = new Error(status.value(), ex.getMessage(), LocalDateTime.now());
			return super.handleExceptionInternal(ex, body, headers, status, request);
		} else

			return super.handleExceptionInternal(ex, body, headers, status, request);
	}

}
