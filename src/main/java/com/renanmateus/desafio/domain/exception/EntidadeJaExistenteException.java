package com.renanmateus.desafio.domain.exception;

public class EntidadeJaExistenteException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EntidadeJaExistenteException(String mensagem) {
		super(mensagem);
	}
}
