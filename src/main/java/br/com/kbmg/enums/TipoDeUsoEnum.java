package br.com.kbmg.enums;

public enum TipoDeUsoEnum {

	PARTICULAR("P"), CORPORATIVO("C"), OUTROS("O");

	private String tipo;

	private TipoDeUsoEnum(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

}
