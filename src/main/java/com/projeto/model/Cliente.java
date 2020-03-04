package com.projeto.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Embeddable
public class Cliente {


    @ManyToOne
    @JoinColumn(name = "patrocinador_codigo")
    private Patrocinador patrocinador;


    @ManyToOne
    @JoinColumn(name = "grupo_codigo")
    private Grupo grupo;


    @JoinColumn(name = "data_aquisicao")
    private LocalDate dataAquisicao;

    public Patrocinador getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(Patrocinador patrocinador) {
        this.patrocinador = patrocinador;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }
}
