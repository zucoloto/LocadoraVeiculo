package br.com.sistema.exemplos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.sistema.model.entity.Carro;

public class CachePrimeiroNivel {

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

	@Test
	public void cache() {
		TypedQuery<Carro> query = this.manager.createQuery("from Carro c", Carro.class);
		List<Carro> carros = query.getResultList();

		for (Carro carro : carros) {
			System.out.println(carro.getId() + " - " + carro.getPlaca());
		}

		System.out.println("--------------------------------------------");
		Carro carro = this.manager.find(Carro.class, 10L);
		System.out.println(carro.getId() + " - " + carro.getPlaca());
	}
	
	@Test
	public void semCache() {
		TypedQuery<Carro> query = this.manager.createQuery("from Carro c", Carro.class);
		List<Carro> carros = query.getResultList();

		for (Carro carro : carros) {
			System.out.println(carro.getId() + " - " + carro.getPlaca());
		}
		
		this.manager.clear();
		this.manager = factory.createEntityManager();

		System.out.println("--------------------------------------------");
		Carro carro = this.manager.find(Carro.class, 10L);
		System.out.println(carro.getId() + " - " + carro.getPlaca());
	}
}
