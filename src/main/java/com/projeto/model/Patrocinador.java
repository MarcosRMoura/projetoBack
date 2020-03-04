package com.projeto.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "patrocinador")
public class Patrocinador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull
    private String razaosocial;

    @NotNull
    @Size(min = 2, max = 10)
    private String nomeabreviado;

    @NotNull
    private Double descontadoemfolha;


    @JsonIgnore
    @ManyToMany(mappedBy = "patrocinadores")
    private List<Grupo> grupos;


    public Patrocinador() {
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getRazaosocial() {
        return razaosocial;
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }

    public String getNomeabreviado() {
        return nomeabreviado;
    }

    public void setNomeabreviado(String nomeabreviado) {
        this.nomeabreviado = nomeabreviado;
    }

    public Double getDescontadoemfolha() {
        return descontadoemfolha;
    }

    public void setDescontadoemfolha(Double descontadoemfolha) {
        this.descontadoemfolha = descontadoemfolha;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
}
