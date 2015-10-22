package br.com.sistema.controller.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sistema.model.dao.FuncionarioDAO;
import br.com.sistema.model.entity.Funcionario;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisarFuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioDAO funcionarioDAO;

	private Funcionario funcionarioSelecionado;

	private List<Funcionario> funcionarios = new ArrayList<>();

	@PostConstruct
	public void inicializar() {
		funcionarios = funcionarioDAO.buscarTodos();
	}

	public void excluir() {
		try {
			funcionarioDAO.excluir(funcionarioSelecionado);
			this.funcionarios.remove(funcionarioSelecionado);
			FacesUtil.addSuccessMessage(funcionarioSelecionado.getNome() + " excluído com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
}
