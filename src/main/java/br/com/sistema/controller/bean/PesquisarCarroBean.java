package br.com.sistema.controller.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sistema.model.dao.CarroDAO;
import br.com.sistema.model.entity.Carro;
import br.com.sistema.model.entitylazy.LazyCarroDataModel;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisarCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CarroDAO carroDAO;

	private Carro carroSelecionado;

	private Carro carroSelecionadoParaExcluir;

	private List<Carro> carros = new ArrayList<>();

	private LazyCarroDataModel lazyCarros;

	@PostConstruct
	public void inicializar() {
		// carros = carroDAO.buscarTodos();
		lazyCarros = new LazyCarroDataModel(carroDAO);
	}

	public void excluir() {
		try {
			carroDAO.excluir(carroSelecionadoParaExcluir);
			this.carros.remove(carroSelecionadoParaExcluir);
			FacesUtil.addSuccessMessage(
					carroSelecionadoParaExcluir.getModelo().getDescricao() + " excluído com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Carro getCarroSelecionado() {
		return carroSelecionado;
	}

	public Carro getCarroSelecionadoParaExcluir() {
		return carroSelecionadoParaExcluir;
	}

	public void setCarroSelecionadoParaExcluir(Carro carroSelecionadoParaExcluir) {
		this.carroSelecionadoParaExcluir = carroSelecionadoParaExcluir;
	}

	public void buscarCarroComAcessorios() {
		carroSelecionado = carroDAO.buscarCarroComAcessorios(carroSelecionado.getId());
	}

	public void setCarroSelecionado(Carro carroSelecionado) {
		this.carroSelecionado = carroSelecionado;
	}

	public List<Carro> getCarros() {
		return carros;
	}

	public LazyCarroDataModel getLazyCarros() {
		return lazyCarros;
	}

}
