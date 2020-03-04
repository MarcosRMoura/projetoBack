package com.projeto.model.metamodel.com.projeto.model;

import com.projeto.model.Cliente;
import com.projeto.model.Grupo;
import com.projeto.model.Patrocinador;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ {

	public static volatile SingularAttribute<Cliente, Grupo> grupo;
	public static volatile SingularAttribute<Cliente, Patrocinador> patrocinador;
	public static volatile SingularAttribute<Cliente, LocalDate> dataAquisicao;

	public static final String GRUPO = "grupo";
	public static final String PATROCINADOR = "patrocinador";
	public static final String DATA_AQUISICAO = "dataAquisicao";

}

