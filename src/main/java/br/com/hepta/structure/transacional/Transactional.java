package br.com.hepta.structure.transacional;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import br.com.hepta.structure.transacional.annotation.Transacional;

@Interceptor
@Transacional
public class Transactional {

	private EntityManager em;

	@Inject
	public Transactional(EntityManager em) {
		this.em = em;
	}

	@AroundInvoke
	public Object executaTransacao(InvocationContext context) throws Exception {
		em.getTransaction().begin();
		try {
			Object result = context.proceed();
			em.flush();
			em.getTransaction().commit();
			return result;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		}
	}

}
