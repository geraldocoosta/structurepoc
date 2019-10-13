package br.com.hepta.structure;

import java.util.Arrays;
import java.util.HashSet;

import javax.persistence.EntityManager;

import br.com.hepta.structure.model.NivelAcesso;
import br.com.hepta.structure.model.Usuario;
import br.com.hepta.structure.util.persistence.HibernateUtil;

public class Teste {
	public static void main(String[] args) {

		HibernateUtil hibernateUtil = new HibernateUtil();
		hibernateUtil.loadEntityManagerFactory();
		EntityManager em = hibernateUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		NivelAcesso nivel = new NivelAcesso();
		nivel.setNomeNivelAcesso("ADMIN");
		NivelAcesso nivel1 = new NivelAcesso();
		nivel1.setNomeNivelAcesso("DEV");
		NivelAcesso nivel2 = new NivelAcesso();
		nivel2.setNomeNivelAcesso("NORMAL");
		em.persist(nivel);
		em.persist(nivel1);
		em.persist(nivel2);
		
		Usuario usuario = new Usuario();
		usuario.setNiveisAcesso(new HashSet<NivelAcesso>(Arrays.asList(nivel, nivel1)));
		usuario.setNome("Geraldo");
		usuario.setPass("1234");
		
		em.persist(usuario);
		
		
		em.getTransaction().commit();
		
	}
}
