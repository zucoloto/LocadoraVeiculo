package br.com.sistema.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.sistema.model.entity.ModeloCarro;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jpa.Transactional;

public class ModeloCarroDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public void salvar(ModeloCarro modeloCarro) {
		em.merge(modeloCarro);
	}

	@Transactional
	public void excluir(ModeloCarro modeloCarro) throws NegocioException {
		modeloCarro = em.find(ModeloCarro.class, modeloCarro.getId());
		try {
			em.remove(modeloCarro);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Este modelo não pode ser excluído!");
		}
	}

	public ModeloCarro buscarPorId(Long id) {
		return em.find(ModeloCarro.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<ModeloCarro> buscarTodos() {
		return em.createQuery("from ModeloCarro").getResultList();
	}
}
