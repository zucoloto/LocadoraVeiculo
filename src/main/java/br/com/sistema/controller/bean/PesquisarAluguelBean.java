package br.com.sistema.controller.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sistema.model.dao.AluguelDAO;
import br.com.sistema.model.dao.ModeloCarroDAO;
import br.com.sistema.model.entity.Aluguel;
import br.com.sistema.model.entity.Carro;
import br.com.sistema.model.entity.ModeloCarro;

@Named
@ViewScoped
public class PesquisarAluguelBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ModeloCarro> modelosCarros;
	private Aluguel aluguel;
	private Carro carro;
	private List<Aluguel> alugueis;

	@Inject
	private ModeloCarroDAO modeloCarroDAO;

	@Inject
	private AluguelDAO aluguelDAO;

	@PostConstruct
	public void inicializar() {
		this.aluguel = new Aluguel();
		this.carro = new Carro();
		this.modelosCarros = this.modeloCarroDAO.buscarTodos();
		this.alugueis = new ArrayList<>();
	}

	public void pesquisar() {
		this.alugueis = aluguelDAO.buscarPorDataDeEntregaEModeloCarro(this.aluguel.getDataEntrega(),
				this.carro.getModelo());
	}

	public List<ModeloCarro> getModelosCarros() {
		return modelosCarros;
	}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public List<Aluguel> getAlugueis() {
		return alugueis;
	}
}
