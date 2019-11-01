package br.com.kbmg.enums;

public enum TipoEndereco {

	F("FISCAL"), O("OUTROS");

	private String tipo;

	private TipoEndereco(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

}
