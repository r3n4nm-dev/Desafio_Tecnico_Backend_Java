package com.renanmateus.desafio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renanmateus.desafio.domain.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

}
