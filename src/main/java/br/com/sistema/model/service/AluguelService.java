package br.com.sistema.model.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.sistema.model.dao.AluguelDAO;
import br.com.sistema.model.entity.Aluguel;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jpa.Transactional;

public class AluguelService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AluguelDAO aluguelDAO;

	@Transactional
	public void salvar(Aluguel aluguel) throws NegocioException {
		if (aluguel.getCarro() == null) {
			throw new NegocioException("O carro é obrigatório!");
		}
		this.aluguelDAO.salvar(aluguel);
	}
}
