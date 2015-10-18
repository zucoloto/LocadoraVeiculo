package br.com.sistema.model.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.sistema.model.entity.Aluguel;

public class AluguelDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public void salvar(Aluguel aluguel) {
		em.merge(aluguel);
	}
}
