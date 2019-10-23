package br.com.kbmg.enums;

public enum TipoPessoa {

	PF("FISICA"), PJ("JURIDICA");

	private String tipo;

	private TipoPessoa(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

}
