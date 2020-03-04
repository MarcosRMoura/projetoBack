package com.projeto.repository.filter;

import com.projeto.model.Grupo;
import com.projeto.model.Plano;

public class GrupoFilter {

    private Long codigo;
    private String nome;
    private Plano plano;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }
}
