package br.com.kbmg.dto;

public class PessoaJuridicaDto {

	private Long idPj;
	private String cnpj;
	private String inscricaoEstadual;

	public Long getIdPj() {
		return idPj;
	}

	public void setIdPj(Long idPj) {
		this.idPj = idPj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

}
