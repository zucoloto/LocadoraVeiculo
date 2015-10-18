package br.com.sistema.controller.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sistema.model.dao.ModeloCarroDAO;
import br.com.sistema.model.entity.ModeloCarro;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisarModeloCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ModeloCarroDAO modeloCarroDAO;

	private ModeloCarro modeloSelecionado;

	private List<ModeloCarro> modelos = new ArrayList<>();

	@PostConstruct
	public void inicializar() {
		modelos = modeloCarroDAO.buscarTodos();
	}

	public void excluir() {
		try {
			modeloCarroDAO.excluir(modeloSelecionado);
			this.modelos.remove(modeloSelecionado);
			FacesUtil.addSuccessMessage(modeloSelecionado.getDescricao() + " excluído com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public ModeloCarro getModeloSelecionado() {
		return modeloSelecionado;
	}

	public void setModeloSelecionado(ModeloCarro modeloSelecionado) {
		this.modeloSelecionado = modeloSelecionado;
	}

	public List<ModeloCarro> getModelos() {
		return modelos;
	}

}