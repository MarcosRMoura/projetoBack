package com.projeto.model.metamodel.com.projeto.model;

import com.projeto.model.Plano;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Plano.class)
public abstract class Plano_ {

	public static volatile SingularAttribute<Plano, Long> codigo;
	public static volatile SingularAttribute<Plano, Double> valormensal;
	public static volatile SingularAttribute<Plano, String> nome;

	public static final String CODIGO = "codigo";
	public static final String VALORMENSAL = "valormensal";
	public static final String NOME = "nome";

}

