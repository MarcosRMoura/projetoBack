package com.projeto.repository.pessoa.grupo;

import com.projeto.model.Pessoa;
import com.projeto.repository.filter.PessoaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaRepositoryQuery {

    public Page<Pessoa> filtrar(PessoaFilter pessoaFilterFilter, Pageable pageable);
}
