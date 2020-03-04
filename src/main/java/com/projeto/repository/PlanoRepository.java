package com.projeto.repository;

import com.projeto.model.Plano;
import com.projeto.repository.plano.PlanoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanoRepository extends JpaRepository<Plano, Long>, PlanoRepositoryQuery {
}
