package com.projeto.repository.grupo;

import com.projeto.model.Grupo;
import com.projeto.repository.filter.GrupoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GrupoRepositoryQuery {

    public Page<Grupo> filtrar(GrupoFilter grupoFilter, Pageable pageable);
}
