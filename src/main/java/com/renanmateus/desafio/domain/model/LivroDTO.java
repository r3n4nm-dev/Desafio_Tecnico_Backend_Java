package com.renanmateus.desafio.domain.model;

import lombok.Data;

@Data
public class LivroDTO {

	private String nome;
	private Integer estoque;

	public LivroDTO(Livro livro) {
	this.nome = livro.getNome();
	this.estoque = livro.getEstoque();
	}
}
