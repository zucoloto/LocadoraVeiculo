package br.com.sistema.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.sistema.model.entity.Carro;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jpa.Transactional;

public class CarroDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public void salvar(Carro carro) {
		em.merge(carro);
	}

	@Transactional
	public void excluir(Carro carro) throws NegocioException {
		carro = buscarPorId(carro.getId());
		try {
			em.remove(carro);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Carro não pode ser excluído!");
		}
	}

	public Carro buscarPorId(Long id) {
		return em.find(Carro.class, id);
	}

	/*
	 * public List<Carro> buscarTodos() { return em.createQuery("from Carro"
	 * ).getResultList(); }
	 */

	@SuppressWarnings("unchecked")
	public List<Carro> buscarTodos() {
		return em.createNamedQuery("Carro.buscarTodos").getResultList();
	}

	/*
	 * public Carro buscarCarroComAcessorios(Long id) { return (Carro)
	 * em.createQuery(
	 * "select c from Carro c LEFT JOIN c.acessorios a where c.id = ?")
	 * .setParameter(1, id) .getSingleResult(); }
	 */

	public Carro buscarCarroComAcessorios(Long id) {
		return em.createNamedQuery("Carro.buscarCarroComAcessorios", Carro.class).setParameter("codigo", id)
				.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Carro> buscarComPaginacao(int first, int pageSize) {
		return em.createNamedQuery("Carro.buscarTodos").setFirstResult(first).setMaxResults(pageSize).getResultList();
	}

	public Long encontrarQuantidadeDeCarros() {
		return em.createQuery("select count(c) from Carro c", Long.class).getSingleResult();
	}
}
