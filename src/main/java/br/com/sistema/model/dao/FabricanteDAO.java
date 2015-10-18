package br.com.sistema.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.sistema.model.entity.Fabricante;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jpa.Transactional;

public class FabricanteDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public void salvar(Fabricante fabricante) {
		em.merge(fabricante);
	}

	@Transactional
	public void excluir(Fabricante fabricante) throws NegocioException {
		fabricante = buscarPorId(fabricante.getId());
		try {
			em.remove(fabricante);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Fabricante não pode ser excluído!");
		}
	}

	public Fabricante buscarPorId(Long id) {
		return em.find(Fabricante.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Fabricante> buscarTodos() {
		return em.createQuery("from Fabricante").getResultList();
	}

}
