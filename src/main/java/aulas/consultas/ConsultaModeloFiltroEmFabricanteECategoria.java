package aulas.consultas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.sistema.util.jpa.JPAUtil;

public class ConsultaModeloFiltroEmFabricanteECategoria {

	public static void main(String[] args) {
		EntityManagerFactory emf = JPAUtil.createEntityManager().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		String jpql = "select mc.descricao from ModeloCarro mc where mc.fabricante.nome = 'Fiat' "
				+ "and mc.categoria in ('SEDAN_MEDIO', 'SEDAN_GRANDE')";

		List<String> modelos = em.createQuery(jpql, String.class).getResultList();

		for (String modelo : modelos) {
			System.out.println("Modelo: " + modelo);
		}

		System.out.println("-----------------------------------------");

		modelos = em.createQuery("select mc.descricao from ModeloCarro mc where mc.fabricante.nome = 'Fiat' "
				+ "and mc.categoria like 'HATCH%'", String.class).getResultList();

		for (String modelo : modelos) {
			System.out.println(modelo);
		}

		em.close();
	}

}
