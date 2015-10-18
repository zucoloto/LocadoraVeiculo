package br.com.sistema.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.sistema.model.entity.Acessorio;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jpa.Transactional;

public class AcessorioDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public void salvar(Acessorio acessorio) {
		em.merge(acessorio);
	}

	@Transactional
	public void excluir(Acessorio acessorio) throws NegocioException {
		acessorio = buscarPorId(acessorio.getId());
		try {
			em.remove(acessorio);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Acessório não pode ser excluído!");
		}
	}

	public Acessorio buscarPorId(Long id) {
		return em.find(Acessorio.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Acessorio> buscarTodos() {
		return em.createQuery("from Acessorio").getResultList();
	}
}
