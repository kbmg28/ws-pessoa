package br.com.kbmg.dto.body;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import br.com.kbmg.enums.TipoDeUsoEnum;

public class EmailBodyDto {

	private String idEmail;
	
	@Email(message="email inválido.")
	private String email;
	
	@NotNull(message="tipoDeUso não informado.")
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
