package br.com.kbmg.dto;

import javax.validation.constraints.NotNull;

import br.com.kbmg.enums.StatusEnum;

public abstract class BaseCadastroDto {

	private ControleInternoDto controleInterno;
	private StatusEnum status;

	@NotNull(message = "pessoaId obrigatório.")
	private String pessoaId;

	public ControleInternoDto getControleInterno() {
		return controleInterno;
	}

	public void setControleInterno(ControleInternoDto controleInterno) {
		this.controleInterno = controleInterno;
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
