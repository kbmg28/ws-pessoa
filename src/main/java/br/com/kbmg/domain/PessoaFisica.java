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
public class PessoaFisica implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_pf;

	@Column
	@NotBlank
	@CPF(message = "CPF inválido.")
	private String cpf;

	@Column
	private String rg;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PESSOA")
	@JsonIgnore
	private Pessoa pessoa;

	
	public Long getId_pf() {
		return id_pf;
	}

	public void setId_pf(Long id_pf) {
		this.id_pf = id_pf;
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
		result = prime * result + ((id_pf == null) ? 0 : id_pf.hashCode());
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
		if (id_pf == null) {
			if (other.id_pf != null)
				return false;
		} else if (!id_pf.equals(other.id_pf))
			return false;
		return true;
	}
}
