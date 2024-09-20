package com.psyconnect.api.service.exceptions;

public class EntidadeNaoEncontradaException extends RuntimeException {

private static final long serialVersionUID= 1L;
	
	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
}
