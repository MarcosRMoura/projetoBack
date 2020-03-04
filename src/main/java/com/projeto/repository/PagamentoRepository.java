package com.projeto.repository;

import com.projeto.model.Grupo;
import com.projeto.model.Pagamento;
import com.projeto.repository.grupo.GrupoRepositoryQuery;
import com.projeto.repository.pagamento.PagamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>, PagamentoRepositoryQuery {
}
