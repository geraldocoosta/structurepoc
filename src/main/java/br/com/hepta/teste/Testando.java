package br.com.hepta.teste;

import javax.persistence.EntityManager;

import br.com.hepta.structure.model.Usuario;
import br.com.hepta.structure.persistence.HibernateUtil;

public class Testando {
	public static void main(String[] args) {
		HibernateUtil hibernateUtil = new HibernateUtil();
		
		hibernateUtil.loadEntityManagerFactory();
		
		EntityManager entityManager = hibernateUtil.getEntityManager();
		System.out.println(entityManager);
		
		entityManager.getTransaction().begin();
		Usuario user = new Usuario();
		user.setNome("Geraldo");
		user.setPass("123456");
		
		entityManager.persist(user);
		
		entityManager.getTransaction().commit();
	}
}
