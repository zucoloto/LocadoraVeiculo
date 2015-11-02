package aulas.consultas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.sistema.util.jpa.JPAUtil;

public class ConsultaPassandoParametros {

	public static void main(String[] args) {
		EntityManagerFactory emf = JPAUtil.createEntityManager().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		String modelo = "Fiat";
		
		System.out.println("Exemplo 1");
		String jpql1 = "select mc.descricao from ModeloCarro mc where mc.fabricante.nome = ?1";

		List<String> modelos1 = em.createQuery(jpql1, String.class).setParameter(1, modelo).getResultList();

		for (String m1 : modelos1) {
			System.out.println(m1);
		}
		
		System.out.println("-------------------");
		
		System.out.println("Exemplo 2");
		String jpql2 = "select mc.descricao from ModeloCarro mc where mc.fabricante.nome = :modelo2";

		List<String> modelos2 = em.createQuery(jpql2, String.class).setParameter("modelo2", modelo).getResultList();

		for (String m2 : modelos2) {
			System.out.println(m2);
		}

		em.close();
	}

}
