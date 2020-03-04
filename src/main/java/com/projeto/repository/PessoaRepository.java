package com.projeto.repository;

import com.projeto.model.Pessoa;
import com.projeto.repository.pessoa.grupo.PessoaRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryQuery {
}
