package br.com.hepta.structure.persistence;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Singleton
public class HibernateUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final String PERSISTENCE_UNIT_NAME = "mercado-test-rest";

	private EntityManagerFactory emf;

	@PostConstruct
	public void loadEntityManagerFactory() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	@Produces
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void close(@Disposes EntityManager em) {
		if (em.isOpen()) {
			em.close();
		}
	}

	@PreDestroy
	public void preDestroy() {
		emf.close();
	}

}
