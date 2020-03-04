package com.projeto.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Grupo.class)
public abstract class Grupo_ {

	public static volatile SingularAttribute<Grupo, Long> codigo;
	public static volatile SingularAttribute<Grupo, Boolean> ativo;
	public static volatile SingularAttribute<Grupo, Plano> plano;
	public static volatile SingularAttribute<Grupo, String> nome;
	public static volatile ListAttribute<Grupo, Patrocinador> patrocinadores;

	public static final String CODIGO = "codigo";
	public static final String ATIVO = "ativo";
	public static final String PLANO = "plano";
	public static final String NOME = "nome";
	public static final String PATROCINADORES = "patrocinadores";

}

