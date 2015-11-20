package br.com.sistema.criteria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.sistema.model.entity.Carro;
import br.com.sistema.model.entity.Categoria;
import br.com.sistema.model.entity.ModeloCarro;

public class ExemplosCascata {

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

	/* 9.1. Gravação em cascata */
	@Test
	public void exemploEntidadeTransiente() {
		Carro carro = new Carro();
		carro.setCor("Preto");
		carro.setPlaca("BBB-1111");

		ModeloCarro modelo = new ModeloCarro();
		modelo.setCategoria(Categoria.ESPORTIVO);
		modelo.setDescricao("Ferrari");

		carro.setModelo(modelo);

		this.manager.getTransaction().begin();
		this.manager.persist(carro);
		this.manager.getTransaction().commit();
	}

}
