package br.com.sistema.model.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.sistema.model.dao.ModeloCarroDAO;
import br.com.sistema.model.entity.ModeloCarro;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jpa.Transactional;

public class ModeloCarroService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ModeloCarroDAO modeloCarroDAO;

	@Transactional
	public void salvar(ModeloCarro modeloCarro) throws NegocioException {
		if (modeloCarro.getDescricao() == null || modeloCarro.getDescricao().trim().equals("")) {
			throw new NegocioException("O nome do MODELO é obrigatório!");
		}
		if (modeloCarro.getFabricante() == null) {
			throw new NegocioException("O FABRICANTE é obrigatório!");
		}
		this.modeloCarroDAO.salvar(modeloCarro);
	}

}
