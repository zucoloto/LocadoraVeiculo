package aulas.consultas;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TemporalType;

import br.com.sistema.util.jpa.JPAUtil;

public class ConsultaAluguelPorData {

	public static void main(String[] args) {
		EntityManagerFactory emf = JPAUtil.createEntityManager().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		Calendar inicioCalendar = Calendar.getInstance();
		inicioCalendar.set(2015, 9, 14, 7, 0); // 14 de outubro de 2015 7:00
												// horas
		Date inicio = inicioCalendar.getTime();

		Calendar fimCalendar = Calendar.getInstance();
		fimCalendar.set(2015, 10, 1, 13, 0); // 1 de novembro de 2015 13:00
												// horas
		Date fim = fimCalendar.getTime();

		String jpql = "select count(a) from Aluguel a where a.dataDevolucao BETWEEN :inicio AND :fim";

		Long quantidadeDevolucoes = em.createQuery(jpql, Long.class)
				.setParameter("inicio", inicio, TemporalType.TIMESTAMP).setParameter("fim", fim, TemporalType.TIMESTAMP)
				.getSingleResult();

		System.out.println("Quantidade de devoluções: " + quantidadeDevolucoes);

		em.close();
	}

}
