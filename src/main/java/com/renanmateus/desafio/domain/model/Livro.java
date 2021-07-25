package com.renanmateus.desafio.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "livros")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Livro {

	//Standard Book Number
	@Id
	@NotNull
	private Long sbn;
	@NotBlank(message="É Obrigatorio.")
	@Column(nullable = false)
	private String nome;
	@NotBlank(message="É Obrigatorio.")
	@Column(nullable = false)
	private String descricao;
	@NotBlank(message="É Obrigatorio.")
	@Column(nullable = false)
	private String autor;
	@NotNull
	@Column(nullable = false)
	private Integer estoque;

}
