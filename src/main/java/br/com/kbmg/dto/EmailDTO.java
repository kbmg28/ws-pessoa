package br.com.kbmg.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import br.com.kbmg.enums.StatusEnum;
import br.com.kbmg.enums.TipoDeUsoEnum;

public class EmailDTO {

	private String idEmail;
	
	@Email(message="email inválido.")
	private String email;
	
	@NotNull(message="tipoDeUso não informado.")
	private TipoDeUsoEnum tipoDeUso;
	
	@NotNull(message="pessoaId obrigatório.")
	private String pessoaId;
	
	private StatusEnum status;

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

	public String getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(String pessoaId) {
		this.pessoaId = pessoaId;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	
}
