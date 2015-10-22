package br.com.sistema.model.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.sistema.model.dao.FuncionarioDAO;
import br.com.sistema.model.entity.Funcionario;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jpa.Transactional;

public class FuncionarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioDAO funcionarioDAO;

	@Transactional
	public void salvar(Funcionario funcionario) throws NegocioException {
		this.funcionarioDAO.salvar(funcionario);
	}
}
