package br.com.sistema.controller.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sistema.model.entity.Funcionario;
import br.com.sistema.model.entity.Sexo;
import br.com.sistema.model.service.FuncionarioService;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastrarFuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioService funcionarioService;

	private Funcionario funcionario;

	private List<Sexo> sexos;

	@PostConstruct
	public void init() {
		limpar();
		this.sexos = Arrays.asList(Sexo.values());
	}

	public void limpar() {
		this.funcionario = new Funcionario();
	}

	public void salvar() {
		try {
			this.funcionarioService.salvar(this.funcionario);
			FacesUtil.addSuccessMessage("Registro salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro desconhecido. Contatar o administrador!");
		}
		limpar();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Sexo> getSexos() {
		return sexos;
	}

}
