package com.projeto.model.metamodel.com.projeto.model;

import com.projeto.model.Grupo;
import com.projeto.model.Patrocinador;
import com.projeto.model.Plano;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Grupo.class)
public abstract class Grupo_ {

	public static volatile SingularAttribute<Grupo, Long> codigo;
	public static volatile SingularAttribute<Grupo, Plano> plano;
	public static volatile SingularAttribute<Grupo, String> nome;
	public static volatile ListAttribute<Grupo, Patrocinador> patrocinadores;

	public static final String CODIGO = "codigo";
	public static final String PLANO = "plano";
	public static final String NOME = "nome";
	public static final String PATROCINADORES = "patrocinadores";

}

