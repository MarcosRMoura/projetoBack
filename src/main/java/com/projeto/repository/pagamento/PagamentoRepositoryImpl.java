package com.projeto.repository.pagamento;

import com.projeto.model.Pagamento;
import com.projeto.model.Pagamento_;
import com.projeto.repository.filter.PagamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PagamentoRepositoryImpl implements PagamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Pagamento> filtrar(PagamentoFilter pagamentoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Pagamento> criteria = builder.createQuery(Pagamento.class);
        Root<Pagamento> root = criteria.from(Pagamento.class);

        Predicate[] predicates = criarRestricoes(pagamentoFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Pagamento> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(pagamentoFilter));
    }


    private Predicate[] criarRestricoes(PagamentoFilter pagamentoFilter, CriteriaBuilder builder,
                                        Root<Pagamento> root) {
        List<Predicate> predicates = new ArrayList<>();

        /*if (!StringUtils.isEmpty(pagamentoFilter.getDescricao())) {
            predicates.add(builder.like(
                    builder.lower(root.get(Pagamento_.descricao)), "%" + pagamentoFilter.getDescricao().toLowerCase() + "%"));
        }*/

        if (pagamentoFilter.getDataPagamentoDe() != null) {
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get(Pagamento_.dataPagamento), pagamentoFilter.getDataPagamentoDe()));
        }

        if (pagamentoFilter.getDataPagamentoAte() != null) {
            predicates.add(
                    builder.lessThanOrEqualTo(root.get(Pagamento_.dataPagamento), pagamentoFilter.getDataPagamentoAte()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Pagamento> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Long total(PagamentoFilter pagamentoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Pagamento> root = criteria.from(Pagamento.class);

        Predicate[] predicates = criarRestricoes(pagamentoFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}