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
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PessoaFisica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPf;

	@Column
	@NotBlank
	@CPF(message = "CPF inválido.")
	private String cpf;

	@Column
	private String rg;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PESSOA_ID")
	@JsonIgnore
	private Pessoa pessoa;

	public PessoaFisica() {

	}

	public PessoaFisica(String cpf, String rg, Pessoa pessoa) {
		this.cpf = cpf;
		this.rg = rg;
		this.pessoa = pessoa;
	}

	public Long getIdPf() {
		return idPf;
	}

	public void setIdPf(Long idPf) {
		this.idPf = idPf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
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
		result = prime * result + ((idPf == null) ? 0 : idPf.hashCode());
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
		PessoaFisica other = (PessoaFisica) obj;
		if (idPf == null) {
			if (other.idPf != null)
				return false;
		} else if (!idPf.equals(other.idPf))
			return false;
		return true;
	}
}
