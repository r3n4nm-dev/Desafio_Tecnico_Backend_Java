package com.renanmateus.desafio.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@Data
public class Error {

	private Integer status;
	private String mensagem;
	private LocalDateTime dataHora;
	private List<Campo> campos;

	
	public Error(int status, String mensagem, LocalDateTime dataHora, List<Campo> campos) {
		super();
		this.status = status;
		this.mensagem = mensagem;
		this.dataHora = dataHora;
		this.campos = campos;
	}

	public Error(int status, String mensagem, LocalDateTime dataHora) {
		super();
		this.status = status;
		this.mensagem = mensagem;
		this.dataHora = dataHora;

	}
}
