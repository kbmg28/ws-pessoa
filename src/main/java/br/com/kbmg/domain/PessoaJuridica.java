package br.com.kbmg.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PessoaJuridica implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_pj;

	@Column
	@NotNull
	@CNPJ(message = "CNPJ inválido.")
	private String cnpj;

	@Column
	private String inscricaoEstadual;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PESSOA")
	@JsonIgnore
	private Pessoa pessoa;
	
	public Long getId_pj() {
		return id_pj;
	}

	public void setId_pj(Long id_pj) {
		this.id_pj = id_pj;
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

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_pj == null) ? 0 : id_pj.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaJuridica other = (PessoaJuridica) obj;
		if (id_pj == null) {
			if (other.id_pj != null)
				return false;
		} else if (!id_pj.equals(other.id_pj))
			return false;
		return true;
	}
}
