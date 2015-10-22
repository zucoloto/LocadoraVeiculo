package br.com.sistema.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.sistema.model.entity.Motorista;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jpa.Transactional;

public class MotoristaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public void salvar(Motorista motorista) {
		em.merge(motorista);
	}

	@Transactional
	public void excluir(Motorista motorista) throws NegocioException {
		motorista = buscarPorId(motorista.getId());
		try {
			em.remove(motorista);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Motorista não pode ser excluído!");
		}
	}

	public Motorista buscarPorId(Long id) {
		return em.find(Motorista.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Motorista> buscarTodos() {
		return em.createQuery("from Motorista").getResultList();
	}
}
