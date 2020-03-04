package com.projeto.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "grupo")
public class Grupo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "plano_codigo")
    private Plano plano;

    @ManyToMany
    @JoinTable(name = "grupo_patrocinador",
            joinColumns = @JoinColumn(name = "grupo_codigo"),
            inverseJoinColumns = @JoinColumn(name = "patrocinador_codigo"))
    private List<Patrocinador> patrocinadores;

    @NotNull
    private String nome;

    private Boolean ativo;


    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public List<Patrocinador> getPatrocinadores() {
        return patrocinadores;
    }

    public void setPatrocinadores(List<Patrocinador> patrocinadores) {
        this.patrocinadores = patrocinadores;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
