package br.com.kbmg.enums;

public enum TipoEndereco {

	FISCAL("FISCAL"), OUTROS("OUTROS");

	private String tipo;

	private TipoEndereco(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

}
