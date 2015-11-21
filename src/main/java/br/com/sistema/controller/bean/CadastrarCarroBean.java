package br.com.sistema.controller.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

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

	private UploadedFile uploadedFile;

	@PostConstruct
	public void init() {
		limpar();
		carregarModelos();
		carregarAcessorios();
	}

	public void limpar() {
		this.carro = new Carro();
		this.carro.setAcessorios(new ArrayList<Acessorio>());
	}

	public void salvar() {
		try {
			if (this.uploadedFile != null) {
				this.carro.setFoto(this.uploadedFile.getContents());
			}
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
		this.modelos = this.modeloDAO.buscarTodos();
	}

	public void carregarAcessorios() {
		this.acessorios = this.acessorioDAO.buscarTodos();
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

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

}
