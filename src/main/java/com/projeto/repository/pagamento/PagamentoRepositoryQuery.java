package com.projeto.repository.pagamento;

import com.projeto.model.Pagamento;
import com.projeto.repository.filter.PagamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PagamentoRepositoryQuery {

    public Page<Pagamento> filtrar(PagamentoFilter pagamentoFilter, Pageable pageable);
}
