package com.projeto.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Patrocinador.class)
public abstract class Patrocinador_ {

	public static volatile SingularAttribute<Patrocinador, String> razaosocial;
	public static volatile SingularAttribute<Patrocinador, Long> codigo;
	public static volatile SingularAttribute<Patrocinador, String> nomeabreviado;
	public static volatile SingularAttribute<Patrocinador, Double> descontadoemfolha;
	public static volatile ListAttribute<Patrocinador, Grupo> grupos;

	public static final String RAZAOSOCIAL = "razaosocial";
	public static final String CODIGO = "codigo";
	public static final String NOMEABREVIADO = "nomeabreviado";
	public static final String DESCONTADOEMFOLHA = "descontadoemfolha";
	public static final String GRUPOS = "grupos";

}

