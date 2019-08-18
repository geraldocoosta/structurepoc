package br.com.hepta.structure.persistence;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

@ApplicationScoped
public class HibernateUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final String PERSISTENCE_UNIT_NAME = "mercado-test-rest";

	private EntityManagerFactory factory;

	@PostConstruct
	public void loadEntityManagerFactory() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	@Produces
	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	@Produces
	public CriteriaBuilder getCriteriaBuilder() {
		return getEntityManager().getCriteriaBuilder();
	}

	public void close(@Disposes EntityManager em) {
		if (em.isOpen()) {
			em.close();
		}
	}

	@PreDestroy
	public void preDestroy() {
		factory.close();
	}
	
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        factory.createEntityManager().close();
    }

}
