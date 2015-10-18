package br.com.sistema.controller.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sistema.model.entity.ModeloEntity;
import br.com.sistema.model.service.ModeloService;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastrarModeloBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ModeloService modeloService;

	private ModeloEntity modeloEntity;

	@Inject
	private ComboDAO comboDAO;

	private List<Combo> combos;

	@PostConstruct
	public void init() {
		limpar();
		carregarCombo();
	}

	public void limpar() {
		this.modeloEntity = new ModeloEntity();
	}

	public void salvar() {
		try {
			this.modeloService.salvar(this.modeloEntity);
			FacesUtil.addSuccessMessage("Registro salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro desconhecido. Contatar o administrador!");
		}
		limpar();
	}

	public void carregarCombo() {
		combos = comboDAO.buscarTodos();
	}

	public ModeloEntity getModeloEntity() {
		return modeloEntity;
	}

	public void setModeloEntity(ModeloEntity modeloEntity) {
		this.modeloEntity = modeloEntity;
	}

	public List<Combo> getCombos() {
		return combos;
	}
}
