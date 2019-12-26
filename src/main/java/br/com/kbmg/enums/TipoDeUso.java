package br.com.kbmg.enums;

public enum TipoDeUso {

	PARTICULAR("PARTICULAR"), CORPORATIVO("CORPORATIVO"), OUTROS("OUTROS");

	private String tipo;

	private TipoDeUso(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

}
