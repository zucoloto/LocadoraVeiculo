package aulas.consultas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.sistema.util.jpa.JPAUtil;

public class ConsultaFabricantesPeloModeloCarro {

	public static void main(String[] args) {
		EntityManagerFactory emf = JPAUtil.createEntityManager().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		List<String> nomeDosFabricantes = em.createQuery("select mc.fabricante.nome from ModeloCarro mc", String.class)
				.getResultList();

		for (String nomeFabricante : nomeDosFabricantes) {
			System.out.println("Nome: " + nomeFabricante);
		}

		em.close();
	}

}
