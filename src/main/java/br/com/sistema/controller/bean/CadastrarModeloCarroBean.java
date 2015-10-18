package br.com.sistema.controller.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sistema.model.dao.FabricanteDAO;
import br.com.sistema.model.entity.Fabricante;
import br.com.sistema.model.entity.ModeloCarro;
import br.com.sistema.model.service.ModeloCarroService;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastrarModeloCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ModeloCarroService modeloCarroService;

	private ModeloCarro modeloCarro;

	@Inject
	private FabricanteDAO fabricanteDAO;

	private List<Fabricante> fabricantes;

	@PostConstruct
	public void init() {
		limpar();
		carregarFabricante();
	}

	public void limpar() {
		this.modeloCarro = new ModeloCarro();
	}

	public void salvar() {
		try {
			this.modeloCarroService.salvar(this.modeloCarro);
			FacesUtil.addSuccessMessage("Registro salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		limpar();
	}

	public void carregarFabricante() {
		fabricantes = fabricanteDAO.buscarTodos();
	}

	public ModeloCarro getModeloCarro() {
		return modeloCarro;
	}

	public void setModeloCarro(ModeloCarro modeloCarro) {
		this.modeloCarro = modeloCarro;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

}
