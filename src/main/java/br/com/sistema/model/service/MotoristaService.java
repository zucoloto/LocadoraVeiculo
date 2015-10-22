package br.com.sistema.model.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.sistema.model.dao.MotoristaDAO;
import br.com.sistema.model.entity.Motorista;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jpa.Transactional;

public class MotoristaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MotoristaDAO motoristaDAO;

	@Transactional
	public void salvar(Motorista motorista) throws NegocioException {
		this.motoristaDAO.salvar(motorista);
	}
}
