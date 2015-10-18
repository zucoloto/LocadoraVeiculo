package br.com.sistema.controller.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sistema.model.dao.CarroDAO;
import br.com.sistema.model.entity.Aluguel;
import br.com.sistema.model.entity.ApoliceSeguro;
import br.com.sistema.model.entity.Carro;
import br.com.sistema.model.service.AluguelService;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastrarAluguelBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AluguelService aluguelService;

	private Aluguel aluguel;

	@Inject
	private CarroDAO carroDAO;

	private List<Carro> carros;

	@PostConstruct
	public void init() {
		limpar();
		carregarCarro();
	}

	public void limpar() {
		this.aluguel = new Aluguel();
		this.aluguel.setApoliceSeguro(new ApoliceSeguro());
	}

	public void salvar() {
		try {
			this.aluguelService.salvar(this.aluguel);
			FacesUtil.addSuccessMessage("Registro salvo com sucesso!");
			limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro desconhecido. Contatar o administrador!");
		}
	}

	public void carregarCarro() {
		carros = carroDAO.buscarTodos();
	}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public List<Carro> getCarros() {
		return carros;
	}

}
