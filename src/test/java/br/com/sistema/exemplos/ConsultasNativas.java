package br.com.sistema.exemplos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.sistema.model.entity.Carro;

public class ConsultasNativas {

	private static EntityManagerFactory factory;

	private EntityManager manager;

	@BeforeClass
	public static void init() {
		factory = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
	}

	@Before
	public void setUp() {
		this.manager = factory.createEntityManager();
	}

	@After
	public void tearDown() {
		this.manager.close();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void consultaNativa() {
		Query query = this.manager.createNativeQuery("select * from carro", Carro.class);
		List<Carro> carros = query.getResultList();

		for (Carro carro : carros) {
			System.out.println(carro.getPlaca());
		}
	}

}
