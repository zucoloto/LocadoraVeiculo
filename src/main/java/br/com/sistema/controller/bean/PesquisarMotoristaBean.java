package br.com.sistema.controller.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sistema.model.dao.MotoristaDAO;
import br.com.sistema.model.entity.Motorista;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisarMotoristaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MotoristaDAO motoristaDAO;

	private Motorista motoristaSelecionado;

	private List<Motorista> motoristas = new ArrayList<>();

	@PostConstruct
	public void inicializar() {
		motoristas = motoristaDAO.buscarTodos();
	}

	public void excluir() {
		try {
			motoristaDAO.excluir(motoristaSelecionado);
			this.motoristas.remove(motoristaSelecionado);
			FacesUtil.addSuccessMessage(motoristaSelecionado.getNome() + " excluído com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Motorista getMotoristaSelecionado() {
		return motoristaSelecionado;
	}

	public void setMotoristaSelecionado(Motorista motoristaSelecionado) {
		this.motoristaSelecionado = motoristaSelecionado;
	}

	public List<Motorista> getMotoristas() {
		return motoristas;
	}
}
