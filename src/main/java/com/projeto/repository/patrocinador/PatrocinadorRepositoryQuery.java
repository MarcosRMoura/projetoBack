package com.projeto.repository.patrocinador;

import com.projeto.model.Patrocinador;
import com.projeto.repository.filter.PatrocinadorFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatrocinadorRepositoryQuery {

    public Page<Patrocinador> filtrar(PatrocinadorFilter patrocinadorFilter, Pageable pageable);
}
