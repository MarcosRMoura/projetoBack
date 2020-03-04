package com.projeto.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pessoa.class)
public abstract class Pessoa_ {

	public static volatile SingularAttribute<Pessoa, Cliente> cliente;
	public static volatile SingularAttribute<Pessoa, Long> codigo;
	public static volatile SingularAttribute<Pessoa, Boolean> ativo;
	public static volatile SingularAttribute<Pessoa, String> cpf;
	public static volatile SingularAttribute<Pessoa, String> nome;
	public static volatile SingularAttribute<Pessoa, LocalDate> dataNascimento;

	public static final String CLIENTE = "cliente";
	public static final String CODIGO = "codigo";
	public static final String ATIVO = "ativo";
	public static final String CPF = "cpf";
	public static final String NOME = "nome";
	public static final String DATA_NASCIMENTO = "dataNascimento";

}

