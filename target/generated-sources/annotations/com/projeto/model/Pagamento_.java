package com.projeto.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pagamento.class)
public abstract class Pagamento_ {

	public static volatile SingularAttribute<Pagamento, Long> codigo;
	public static volatile SingularAttribute<Pagamento, Pessoa> pessoa;
	public static volatile SingularAttribute<Pagamento, LocalDate> dataPagamento;
	public static volatile SingularAttribute<Pagamento, Double> valor;

	public static final String CODIGO = "codigo";
	public static final String PESSOA = "pessoa";
	public static final String DATA_PAGAMENTO = "dataPagamento";
	public static final String VALOR = "valor";

}

