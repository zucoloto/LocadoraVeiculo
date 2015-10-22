package br.com.sistema.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.sistema.model.entity.Funcionario;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jpa.Transactional;

public class FuncionarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public void salvar(Funcionario funcionario) {
		em.merge(funcionario);
	}

	@Transactional
	public void excluir(Funcionario funcionario) throws NegocioException {
		funcionario = buscarPorId(funcionario.getId());
		try {
			em.remove(funcionario);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Funcionario não pode ser excluído!");
		}
	}

	public Funcionario buscarPorId(Long id) {
		return em.find(Funcionario.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> buscarTodos() {
		return em.createQuery("from Funcionario").getResultList();
	}
}
