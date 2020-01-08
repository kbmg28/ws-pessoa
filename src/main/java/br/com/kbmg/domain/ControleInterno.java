package br.com.kbmg.domain;

import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Embeddable
public class ControleInterno {

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

	@PrePersist
	public void PrePersist() {
		setDataCriacao(LocalDate.now());
		setDataAtualizacao(LocalDate.now());
	}

	@PreUpdate
	public void PreUpdate() {
		if (this.dataCriacao == null)
			throw new IllegalArgumentException("Erro ao atualizar, data de criação não encontrada.");
		
		setDataAtualizacao(LocalDate.now());
	}
}
