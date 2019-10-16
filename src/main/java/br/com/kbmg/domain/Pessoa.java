package br.com.kbmg.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private Long codPessoa;
	
	private String nomeCompleto;

	public Long getCodPessoa() {
		return codPessoa;
	}

	public void setCodPessoa(Long codPessoa) {
		this.codPessoa = codPessoa;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codPessoa == null) ? 0 : codPessoa.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (codPessoa == null) {
			if (other.codPessoa != null)
				return false;
		} else if (!codPessoa.equals(other.codPessoa))
			return false;
		return true;
	}

	
	
}
