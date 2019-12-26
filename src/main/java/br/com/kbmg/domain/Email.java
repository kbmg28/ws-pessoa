package br.com.kbmg.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.kbmg.enums.TipoDeUso;

@Entity
public class Email implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEmail;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PESSOA_ID")
	@JsonIgnore
	private Pessoa pessoa;

	@Column
	@javax.validation.constraints.Email
	@NotNull
	private String email;

	@Column
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Tipo de uso do email inválido.")
	private TipoDeUso tipoDeUso;

	public Long getIdEmail() {
		return idEmail;
	}

	public void setIdEmail(Long idEmail) {
		this.idEmail = idEmail;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoDeUso getTipoDeUso() {
		return tipoDeUso;
	}

	public void setTipoDeUso(TipoDeUso tipoDeUso) {
		this.tipoDeUso = tipoDeUso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEmail == null) ? 0 : idEmail.hashCode());
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
		Email other = (Email) obj;
		if (idEmail == null) {
			if (other.idEmail != null)
				return false;
		} else if (!idEmail.equals(other.idEmail))
			return false;
		return true;
	}

}
