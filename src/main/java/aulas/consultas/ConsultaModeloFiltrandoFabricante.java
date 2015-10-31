package aulas.consultas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.sistema.util.jpa.JPAUtil;

public class ConsultaModeloFiltrandoFabricante {

	public static void main(String[] args) {
		EntityManagerFactory emf = JPAUtil.createEntityManager().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		List<String> modelos = em
				.createQuery("select mc.descricao from ModeloCarro mc where mc.fabricante.nome = 'Fiat'", String.class)
				.getResultList();

		for (String modelo : modelos) {
			System.out.println("Modelo: " + modelo);
		}

		em.close();
	}

}
