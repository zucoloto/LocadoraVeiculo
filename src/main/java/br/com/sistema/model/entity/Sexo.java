package br.com.sistema.model.entity;

public enum Sexo {
	M("Masculino"), F("Feminino");

	private String descricao;

	Sexo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
