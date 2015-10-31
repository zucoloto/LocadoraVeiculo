package aulas.consultas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.sistema.util.jpa.JPAUtil;

public class ConsultaFabricantes {

	public static void main(String[] args) {
		EntityManagerFactory emf = JPAUtil.createEntityManager().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		List<String> nomesDosFabricantes = em.createQuery("select f.nome from Fabricante f", String.class)
				.getResultList();

		for (String nome : nomesDosFabricantes) {
			System.out.println("Nome: " + nome);
		}

		em.close();

	}

}
