package br.com.sistema.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.sistema.model.entity.ModeloEntity;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jpa.Transactional;

public class ModeloDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public void salvar(ModeloEntity modeloEntity) {
		em.merge(modeloEntity);
	}

	@Transactional
	public void excluir(ModeloEntity modeloEntity) throws NegocioException {
		modeloEntity = em.find(ModeloEntity.class, modeloEntity.getId());
		try {
			em.remove(modeloEntity);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("O registro não pode ser excluído!");
		}
	}

	public ModeloEntity buscarPorId(Long id) {
		return em.find(ModeloEntity.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<ModeloEntity> buscarTodos() {
		return em.createQuery("from ModeloEntity").getResultList();
	}
}
