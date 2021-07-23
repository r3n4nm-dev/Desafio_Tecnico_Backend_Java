package com.renanmateus.desafio.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "livros")
@Data
public class Livro {

	//Standard Book Number
	@Id
	private Long sbn;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String descricao;
	@Column(nullable = false)
	private String autor;
	@Column(nullable = false)
	private Integer estoque;

}
