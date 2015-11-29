package br.com.sistema.model.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.sistema.model.dao.AcessorioDAO;
import br.com.sistema.model.entity.Acessorio;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jpa.Transactional;

public class AcessorioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AcessorioDAO acessorioDAO;

	@Transactional
	public void salvar(Acessorio acessorio) throws NegocioException {
		this.acessorioDAO.salvar(acessorio);
	}

}
