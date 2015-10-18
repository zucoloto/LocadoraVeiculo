package br.com.sistema.model.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.sistema.model.dao.ModeloDAO;
import br.com.sistema.model.entity.ModeloEntity;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jpa.Transactional;

public class ModeloService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ModeloDAO modeloDAO;

	@Transactional
	public void salvar(ModeloEntity modeloEntity) throws NegocioException {
		if (modeloEntity.getNome1() == null || modeloEntity.getNome1().trim().equals("")) {
			throw new NegocioException("O nome do CAMPO é obrigatório!");
		}

		if (modeloEntity.getNome2() == null) {
			throw new NegocioException("O CAMPO é obrigatório!");
		}

		this.modeloDAO.salvar(modeloEntity);
	}
}
