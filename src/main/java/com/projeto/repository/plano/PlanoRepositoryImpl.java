package com.projeto.repository.plano;

import com.projeto.model.Grupo;
import com.projeto.model.Grupo_;
import com.projeto.model.Plano;
import com.projeto.model.Plano_;
import com.projeto.repository.filter.GrupoFilter;
import com.projeto.repository.filter.PlanoFilter;
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

public class PlanoRepositoryImpl implements PlanoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Plano> filtrar(PlanoFilter planoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Plano> criteria = builder.createQuery(Plano.class);
        Root<Plano> root = criteria.from(Plano.class);

        Predicate[] predicates = criarRestricoes(planoFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Plano> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(planoFilter));
    }


    private Predicate[] criarRestricoes(PlanoFilter planoFilter, CriteriaBuilder builder,
                                        Root<Plano> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(planoFilter.getNome())) {
            predicates.add(builder.like(
                    builder.lower(root.get(Plano_.nome)), "%" + planoFilter.getNome().toLowerCase() + "%"));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Plano> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Long total(PlanoFilter planoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Plano> root = criteria.from(Plano.class);

        Predicate[] predicates = criarRestricoes(planoFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}