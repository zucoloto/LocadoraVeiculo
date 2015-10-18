package br.com.sistema.controller.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sistema.model.dao.ModeloDAO;
import br.com.sistema.model.entity.ModeloEntity;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisarModeloBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ModeloDAO modeloDAO;

	private ModeloEntity modeloSelecionado;

	private List<ModeloEntity> modelos = new ArrayList<>();

	@PostConstruct
	public void inicializar() {
		modelos = modeloDAO.buscarTodos();
	}

	public void excluir() {
		try {
			modeloDAO.excluir(modeloSelecionado);
			this.modelos.remove(modeloSelecionado);
			FacesUtil.addSuccessMessage(modeloSelecionado.getNome1() + " excluído com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public ModeloEntity getModeloSelecionado() {
		return modeloSelecionado;
	}

	public void setModeloSelecionado(ModeloEntity modeloSelecionado) {
		this.modeloSelecionado = modeloSelecionado;
	}

	public List<ModeloEntity> getModelos() {
		return modelos;
	}
}
