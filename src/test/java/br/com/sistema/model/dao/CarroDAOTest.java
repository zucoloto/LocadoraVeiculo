package br.com.sistema.model.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

//import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.JPAHelper;

import br.com.sistema.model.entity.Carro;

public class CarroDAOTest {

	// private JIntegrity helper = new JIntegrity();

	private CarroDAO carroDAO;

	@Before
	public void init() {
		// Não funcionou
		// helper.cleanAndInsert();

		this.carroDAO = new CarroDAO();
		this.carroDAO.setEm(JPAHelper.currentEntityManager());
	}

	@Test
	public void buscarCarroPeloId() {
		Carro carro = this.carroDAO.buscarPorId(1L);

		assertEquals(1L, carro.getId().longValue());
		assertEquals("AAA-1111", carro.getPlaca());
	}
}
