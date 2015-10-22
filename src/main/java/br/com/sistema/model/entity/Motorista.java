package br.com.sistema.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MOTORISTA")
public class Motorista extends Pessoa {

	private static final long serialVersionUID = 1L;

	private String numeroCNH;

	public String getNumeroCNH() {
		return numeroCNH;
	}

	public void setNumeroCNH(String numeroCNH) {
		this.numeroCNH = numeroCNH;
	}

}
