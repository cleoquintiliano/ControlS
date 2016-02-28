package com.cqi.controls.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author cqfb
 */

@ApplicationScoped
public class EntityManagerProducer {

	private EntityManagerFactory factory;

	public EntityManagerProducer() {
		factory = Persistence.createEntityManagerFactory("ControlsPU");
	}

	/*
	 * Anotação @Produces torna o metodo capaz de criar objetos com contrutor
	 * com argumentos e com parametro do tipo Class (o qual o Weld não sabe
	 * instanciar) Dessa forma o Weld sabe que sempre que for necessário criar
	 * um objeto do tipo DAO (Data Access Object), ele deverá criá-lo através
	 * deste factory method.
	 */
	@Produces
	@RequestScoped
	public EntityManager createEntityManager() {
		return factory.createEntityManager();
	}

	/*
	 * O CDI permite executar algo ao final da requsição, Como neste caso é
	 * necessario chamar o close no EntityManager, então o método recebe a
	 * dependência a ser descartada pela anotação do parâmetro com
	 * @Disposes
	 */
	public void closeEntityManager(@Disposes EntityManager manager) {
		manager.close();
	}

}
