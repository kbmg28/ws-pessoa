package br.com.kbmg.enums;

public enum StatusEnum {

	ATIVO("A"), INATIVO("I");

	private String status;

	private StatusEnum(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
