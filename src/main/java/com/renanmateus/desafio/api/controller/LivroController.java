package com.renanmateus.desafio.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		Page<LivroDTO> livrosDTO = livroService.listarLivrosEmEstoque(pageable);
 		return livrosDTO;
	}
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping
	public Livro salvar(@Valid @RequestBody Livro livro) {
		return livroService.salvar(livro);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "/{sbn}")
	public Livro buscarPorSBN(@PathVariable Long sbn) {
		return livroService.buscarPorSbn(sbn);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping(value = "/{sbn}")
	public Livro editar(@PathVariable Long sbn, @RequestBody @Valid LivroDTO livrodto) {
		return livroService.atualizar(sbn, livrodto);
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/{sbn}")
	public void remover(@PathVariable Long sbn) {
		livroService.deletar(sbn);
	}
}
