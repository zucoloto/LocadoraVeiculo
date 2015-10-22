package br.com.sistema.controller.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sistema.model.entity.Motorista;
import br.com.sistema.model.entity.Sexo;
import br.com.sistema.model.service.MotoristaService;
import br.com.sistema.model.service.exception.NegocioException;
import br.com.sistema.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastrarMotoristaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MotoristaService motoristaService;

	private Motorista motorista;

	private List<Sexo> sexos;

	@PostConstruct
	public void init() {
		limpar();
		this.sexos = Arrays.asList(Sexo.values());
	}

	public void limpar() {
		this.motorista = new Motorista();
	}

	public void salvar() {
		try {
			this.motoristaService.salvar(this.motorista);
			FacesUtil.addSuccessMessage("Registro salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro desconhecido. Contatar o administrador!");
		}
		limpar();
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public List<Sexo> getSexos() {
		return sexos;
	}

}
