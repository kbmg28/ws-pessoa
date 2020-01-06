package br.com.kbmg.enums;

public enum TipoEnderecoEnum {

	FISCAL("FISCAL"), OUTROS("OUTROS");

	private String tipo;

	private TipoEnderecoEnum(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

}
