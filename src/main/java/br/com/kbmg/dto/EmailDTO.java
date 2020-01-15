package br.com.kbmg.dto;

import br.com.kbmg.enums.TipoDeUsoEnum;

public class EmailDTO extends BaseCadastroDto{

	private String idEmail;
	private String email;
	private TipoDeUsoEnum tipoDeUso;
	
	public String getIdEmail() {
		return idEmail;
	}

	public void setIdEmail(String idEmail) {
		this.idEmail = idEmail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoDeUsoEnum getTipoDeUso() {
		return tipoDeUso;
	}

	public void setTipoDeUso(TipoDeUsoEnum tipoDeUso) {
		this.tipoDeUso = tipoDeUso;
	}

}
