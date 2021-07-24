package com.renanmateus.desafio.domain.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL) // ignore all null fields
@Data

public class LivroDTO {

	@NotNull
	private Integer estoque;
	@NotBlank(message = " É Obrigatorio.")
	private String nome;
	@NotBlank(message = " É Obrigatorio.")
	private String descricao;
	@NotBlank(message = " É Obrigatorio.")
	private String autor;

	
	public LivroDTO(Livro livro) {
		this.nome = livro.getNome();
		this.estoque = livro.getEstoque();
	}

	public LivroDTO() {
	}
}
