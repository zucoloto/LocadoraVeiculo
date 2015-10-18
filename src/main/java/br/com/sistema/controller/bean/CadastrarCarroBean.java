package br.com.sistema.controller.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sistema.model.dao.AcessorioDAO;
import br.com.sistema.model.dao.ModeloCarroDAO;
import br.com.sistema.model.entity.Acessorio;
import br.com.sistema.model.entity.Carro;
import br.com.sistema.model.entity.ModeloCarro;
import br.com.sistema.model.service.CarroService;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastrarCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CarroService carroService;

	private Carro carro;

	@Inject
	private ModeloCarroDAO modeloDAO;

	private List<ModeloCarro> modelos;

	@Inject
	private AcessorioDAO acessorioDAO;

	private List<Acessorio> acessorios;

	@PostConstruct
	public void init() {
		limpar();
		carregarModelos();
		carregarAcessorios();
	}

	public void limpar() {
		this.carro = new Carro();
	}

	public void salvar() {
		try {
			this.carroService.salvar(this.carro);
			FacesUtil.addSuccessMessage("Registro salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro desconhecido. Contatar o administrador!");
		}
		limpar();
	}

	public void carregarModelos() {
		modelos = modeloDAO.buscarTodos();
	}

	public void carregarAcessorios() {
		acessorios = acessorioDAO.buscarTodos();
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public List<ModeloCarro> getModelos() {
		return modelos;
	}

	public List<Acessorio> getAcessorios() {
		return acessorios;
	}

}
