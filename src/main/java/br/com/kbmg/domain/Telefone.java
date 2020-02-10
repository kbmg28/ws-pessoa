package br.com.kbmg.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.kbmg.enums.StatusEnum;
import br.com.kbmg.enums.TipoDeUsoEnum;

@Entity
public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTelefone;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PESSOA_ID")
	@JsonIgnore
	private Pessoa pessoa;
	
	@Column
	@NotNull
	private Integer ddd;

	@Column
	@NotNull
	private Integer numero;

	@Column
	@NotNull
	private String contato;

	@Column
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Tipo de uso do telefone inválido.")
	private TipoDeUsoEnum tipoDeUso;

	@Embedded
	private ControleInterno controleInterno = new ControleInterno();

	@NotNull
	private StatusEnum status;
	
	public Long getIdTelefone() {
		return idTelefone;
	}

	public void setIdTelefone(Long idTelefone) {
		this.idTelefone = idTelefone;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public Integer getDdd() {
		return ddd;
	}
	
	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public TipoDeUsoEnum getTipoDeUso() {
		return tipoDeUso;
	}

	public void setTipoDeUso(TipoDeUsoEnum tipoDeUso) {
		this.tipoDeUso = tipoDeUso;
	}

	public ControleInterno getControleInterno() {
		return controleInterno;
	}

	public void setControleInterno(ControleInterno controleInterno) {
		this.controleInterno = controleInterno;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTelefone == null) ? 0 : idTelefone.hashCode());
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
		Telefone other = (Telefone) obj;
		if (idTelefone == null) {
			if (other.idTelefone != null)
				return false;
		} else if (!idTelefone.equals(other.idTelefone))
			return false;
		return true;
	}

	@PrePersist
	public void PrePersist() {
		setStatus(StatusEnum.ATIVO);
	}

}
