package br.com.kbmg.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import br.com.kbmg.enums.StatusEnum;

public abstract class BaseCadastroDto {

	private LocalDate dataModificacao;
	private StatusEnum status;

	@NotNull(message = "pessoaId obrigatório.")
	private String pessoaId;

	public LocalDate getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(LocalDate dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public String getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(String pessoaId) {
		this.pessoaId = pessoaId;
	}

	
}
