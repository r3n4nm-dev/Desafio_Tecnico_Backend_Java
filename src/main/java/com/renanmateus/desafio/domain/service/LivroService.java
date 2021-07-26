package com.renanmateus.desafio.domain.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.renanmateus.desafio.domain.exception.EntidadeJaExistenteException;
import com.renanmateus.desafio.domain.exception.EntidadeNaoEncontradaException;
import com.renanmateus.desafio.domain.model.Livro;
import com.renanmateus.desafio.domain.model.LivroDTO;
import com.renanmateus.desafio.domain.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;

	public Livro salvar(Livro livro) {
		Optional<Livro> sbn = livroRepository.findById(livro.getSbn());
		if (sbn.isEmpty()) {
			return livroRepository.save(livro);
		}
		throw new EntidadeJaExistenteException("Objeto já inserido");
	}

	public Page<LivroDTO> listarLivrosEmEstoque(Pageable pageable) {
		Page<Livro> livros = livroRepository.findByEstoqueGreaterThan(0, pageable);
		Page<LivroDTO> livrosDTO = livros.map(livro -> new LivroDTO(livro.getNome()));
		
		return livrosDTO;
	}

	public Livro buscarPorSbn(Long sbn) {
		Optional<Livro> livro = livroRepository.findById(sbn);
		return livro.orElseThrow(() -> new EntidadeNaoEncontradaException("Objeto não encontrado."));

	}

	public Livro atualizar(Long sbn, LivroDTO livrodto) {
		if (livroRepository.findById(sbn).isPresent()) {
			Livro livroSalvo = new Livro();
			BeanUtils.copyProperties(livrodto, livroSalvo, "sbn");
			livroSalvo.setSbn(sbn);
			livroRepository.save(livroSalvo);
			return livroSalvo;
		}
		throw new EntidadeNaoEncontradaException("Objeto não encontrado.");
	}

	public void deletar(Long sbn) {
		if (livroRepository.findById(sbn).isPresent()) {
			livroRepository.deleteById(sbn);
		} else
		throw new EntidadeNaoEncontradaException("Objeto não encontrado.");
	}

}