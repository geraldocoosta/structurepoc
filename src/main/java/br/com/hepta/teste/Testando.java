package br.com.hepta.teste;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.hepta.structure.model.Usuario;
import br.com.hepta.structure.model.enums.NivelAcesso;
import br.com.hepta.structure.persistence.HibernateUtil;

public class Testando {
	public static void main(String[] args) {
		HibernateUtil hibernateUtil = new HibernateUtil();

		hibernateUtil.loadEntityManagerFactory();

		EntityManager entityManager = hibernateUtil.getEntityManager();

		entityManager.getTransaction().begin();

		 @SuppressWarnings("unchecked")
		List<Object[]> resultList = entityManager.createNativeQuery("SELECT user.id, user.nome, nivel.niveisAcesso "
				+ "FROM usuario_niveisacesso nivel INNER JOIN usuario user "
				+ "ON nivel.Usuario_id = user.id where user.nome = :nome AND user.pass = :pass")
			.setParameter("nome", "Geraldo")
			.setParameter("pass", "123456")
			.getResultList();
		
		Usuario usuario = null;
		Set<NivelAcesso> niveisAcesso = new HashSet<>(); 
		
		for(Object[] obj: resultList) {
			usuario = new Usuario(Integer.parseInt(obj[0].toString().trim()),obj[1].toString());
			NivelAcesso nivelAcesso = NivelAcesso.valueOf(obj[2].toString().trim());
			niveisAcesso.add(nivelAcesso);
		}
		usuario.setNiveisAcesso(niveisAcesso);
		System.out.println(usuario);
		
		
		entityManager.getTransaction().commit();

	}
}
