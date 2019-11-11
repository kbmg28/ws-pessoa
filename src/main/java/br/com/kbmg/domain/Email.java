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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Email  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_email;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PESSOA")
	@JsonIgnore
	private Pessoa pessoa;

	@Column
	@javax.validation.constraints.Email
	private String email;

	public Long getId_email() {
		return id_email;
	}

	public void setId_email(Long id_email) {
		this.id_email = id_email;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_email == null) ? 0 : id_email.hashCode());
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
		if (id_email == null) {
			if (other.id_email != null)
				return false;
		} else if (!id_email.equals(other.id_email))
			return false;
		return true;
	}
	
}
