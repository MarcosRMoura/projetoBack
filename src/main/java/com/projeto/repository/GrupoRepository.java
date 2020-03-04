package com.projeto.repository;

import com.projeto.model.Grupo;
import com.projeto.repository.grupo.GrupoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo, Long>, GrupoRepositoryQuery {
}
