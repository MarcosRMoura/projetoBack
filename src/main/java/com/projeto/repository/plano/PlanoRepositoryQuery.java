package com.projeto.repository.plano;

import com.projeto.model.Grupo;
import com.projeto.model.Plano;
import com.projeto.repository.filter.GrupoFilter;
import com.projeto.repository.filter.PlanoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlanoRepositoryQuery {

    public Page<Plano> filtrar(PlanoFilter planoFilter, Pageable pageable);
}
