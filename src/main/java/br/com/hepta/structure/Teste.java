package br.com.hepta.structure;

import java.util.HashSet;

import javax.persistence.EntityManager;

import br.com.hepta.structure.model.Usuario;
import br.com.hepta.structure.model.enums.NivelAcesso;
import br.com.hepta.structure.util.persistence.HibernateUtil;

public class Teste {
	public static void main(String[] args) {

		HibernateUtil hibernateUtil = new HibernateUtil();
		hibernateUtil.loadEntityManagerFactory();
		EntityManager em = hibernateUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		HashSet<NivelAcesso> hashSet = new HashSet<NivelAcesso>();
		hashSet.add(NivelAcesso.ADMIN);
		hashSet.add(NivelAcesso.DEV);
		
		Usuario user = new Usuario();
		user.setNome("Geraldo");
		user.setPass("1234");
		user.setNiveisAcesso(hashSet);
		
		em.persist(user);
		
		em.getTransaction().commit();
		
	}
}
