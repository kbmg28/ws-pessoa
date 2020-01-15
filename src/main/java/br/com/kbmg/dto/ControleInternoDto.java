package br.com.kbmg.dto;

import java.time.LocalDateTime;

public class ControleInternoDto {

	private LocalDateTime dataCriacao;
	private LocalDateTime dataAtualizacao;
	
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	
}
