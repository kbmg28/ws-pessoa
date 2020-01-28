package br.com.kbmg.domain;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Embeddable
public class ControleInterno {

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

	@PrePersist
	public void PrePersist() {
		setDataCriacao(LocalDateTime.now());
		setDataAtualizacao(LocalDateTime.now());
	}

	@PreUpdate
	public void PreUpdate() {
		if (this.dataCriacao == null)
			throw new IllegalArgumentException("Erro ao atualizar, data de criação não encontrada.");
		
		setDataAtualizacao(LocalDateTime.now());
	}
}
