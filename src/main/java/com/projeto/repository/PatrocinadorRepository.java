package com.projeto.repository;

import com.projeto.model.Patrocinador;
import com.projeto.repository.patrocinador.PatrocinadorRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatrocinadorRepository extends JpaRepository<Patrocinador, Long>, PatrocinadorRepositoryQuery {
}
