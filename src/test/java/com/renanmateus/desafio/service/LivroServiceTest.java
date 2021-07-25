package com.renanmateus.desafio.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.transaction.TransactionSystemException;

import com.renanmateus.desafio.domain.exception.EntidadeJaExistenteException;
import com.renanmateus.desafio.domain.exception.EntidadeNaoEncontradaException;
import com.renanmateus.desafio.domain.model.Livro;
import com.renanmateus.desafio.domain.model.LivroDTO;
import com.renanmateus.desafio.domain.repository.LivroRepository;
import com.renanmateus.desafio.domain.service.LivroService;

@SpringBootTest
public class LivroServiceTest {

	@Autowired
	private LivroService livroService;
	@Autowired
	private LivroRepository livroRepository;

	private Livro livroTeste; 

	@BeforeEach
	public void setUp() {
	
		livroTeste = Livro.builder()
		.sbn(1L)
		.nome("Livro teste 1")
		.descricao("Descricao teste 1")
		.autor("Autor teste 1")
		.estoque(1)
		.build();
		
		livroRepository.deleteAll();
	}

	@Test
	public void deveRetornarLivro_QuandoCadastrarLivroComSucesso() {
		livroTeste = livroService.salvar(livroTeste);
		assertThat(livroTeste).isNotNull();
	}

	@Test
	public void deveFalhar_QuandoCadastrarLivroJaExistente() {
		livroService.salvar(livroTeste);
		EntidadeJaExistenteException erroEsperado = Assertions.assertThrows(EntidadeJaExistenteException.class, () -> {
			livroService.salvar(livroTeste);
		});
		assertThat(erroEsperado).isNotNull();
	}

	@Test
	public void deveFalhar_QuandoCadastrarLivroSemSbn() {
		Livro novoLivro = new Livro(null, null, null, null, null);
		InvalidDataAccessApiUsageException erroEsperado = Assertions
				.assertThrows(InvalidDataAccessApiUsageException.class, () -> {
					livroService.salvar(novoLivro);
				});
		assertThat(erroEsperado).isNotNull();
	}

	@Test
	public void deveFalhar_QuandoCadastrarLivroSemArgumentos() {
		Livro novoLivro = new Livro(1L, null, null, null, null);
		TransactionSystemException erroEsperado = Assertions.assertThrows(TransactionSystemException.class, () -> {
			livroService.salvar(novoLivro);
		});
		assertThat(erroEsperado).isNotNull();
	}

	@Test
	public void deveRetornarLivro_QuandoBuscarPorSbn() {
		livroService.salvar(livroTeste);
		livroTeste = livroService.buscarPorSbn(livroTeste.getSbn());
		assertThat(livroTeste).isNotNull();
	}

	@Test
	public void deveFalhar_QuandoBuscarPorSbnInexistente() {
		EntidadeNaoEncontradaException erroEsperado = Assertions.assertThrows(EntidadeNaoEncontradaException.class,
				() -> {
					livroService.buscarPorSbn(1L);
				});
		assertThat(erroEsperado).isNotNull();
	}

	@Test
	public void deveEditarLivro_QuandoBuscarPorSbn() {
		livroService.salvar(livroTeste);
		livroTeste = livroService.atualizar(livroTeste.getSbn(), new LivroDTO(livroTeste));
		assertThat(livroTeste).isNotNull();
	}

	@Test
	public void deveFalharAoEditarLivro_QuandoAdcionarLivroSemArgumentos() {
		livroService.salvar(livroTeste);
		TransactionSystemException erroEsperado = Assertions.assertThrows(TransactionSystemException.class, () -> {
			livroService.atualizar(livroTeste.getSbn(), new LivroDTO());
		});
		assertThat(erroEsperado).isNotNull();
	}

	@Test
	public void deveRemoverLivroComSucesso_QuandoBuscarPorSbn() {
		livroService.salvar(livroTeste);
		Assertions.assertDoesNotThrow(()-> livroService.deletar(livroTeste.getSbn()));
	}

	@Test
	public void deveFalharAoRemoverLivro_QuandoBuscarPorSbn() {
		EntidadeNaoEncontradaException erroEsperado = Assertions.assertThrows(EntidadeNaoEncontradaException.class,
				() -> {
					livroService.deletar(1L);
				});
		assertThat(erroEsperado).isNotNull();
	}
}