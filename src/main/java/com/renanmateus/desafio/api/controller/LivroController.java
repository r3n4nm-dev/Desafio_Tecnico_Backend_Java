package com.renanmateus.desafio.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.renanmateus.desafio.domain.model.Livro;
import com.renanmateus.desafio.domain.model.LivroDTO;
import com.renanmateus.desafio.domain.service.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivroController {

	@Autowired
	private LivroService livroService;

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping()
	public Page<LivroDTO> listar(Pageable pageable) {
		Page<Livro> livros = livroService.listarLivrosEmEstoque(pageable);
		Page<LivroDTO> livrosDTO = livros.map(livro -> new LivroDTO(livro));
		return livrosDTO;
	}

	@PostMapping
	public Livro salvar(@RequestBody Livro livro) {
		return livroService.salvar(livro);
	}

	@GetMapping(value = "/{sbn}") 
		public Livro buscarPorSBN( @PathVariable Long sbn) {
		return livroService.buscarPorSbn(sbn);
	}
}
