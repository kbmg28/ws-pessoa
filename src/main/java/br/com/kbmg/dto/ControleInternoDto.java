package br.com.kbmg.dto;

import java.time.LocalDate;

public class ControleInternoDto {

	private LocalDate dataCriacao;
	private LocalDate dataAtualizacao;

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDate getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDate dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
}
