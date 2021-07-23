package com.renanmateus.desafio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renanmateus.desafio.domain.model.Livro;
import com.renanmateus.desafio.domain.service.LivroService;


@RestController
@RequestMapping(value = "/livros")
public class LivroController {

	@Autowired
	private LivroService livroService;
	
	@PostMapping
	public Livro salvar(@RequestBody Livro livro) {
		return livroService.salvar(livro);

	}

	
	
}
