package br.com.sistema.model.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.sistema.model.dao.CarroDAO;
import br.com.sistema.model.entity.Carro;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jpa.Transactional;

public class CarroService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CarroDAO carroDAO;

	@Transactional
	public void salvar(Carro carro) throws NegocioException {
		if (carro.getPlaca() == null || carro.getPlaca().trim().equals("")) {
			throw new NegocioException("A PLACA é obrigatória!");
		}
		this.carroDAO.salvar(carro);
	}

}
