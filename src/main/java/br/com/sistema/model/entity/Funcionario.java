package br.com.sistema.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FUNCIONARIO")
public class Funcionario extends Pessoa {

	private static final long serialVersionUID = 1L;

	private String matricula;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}
