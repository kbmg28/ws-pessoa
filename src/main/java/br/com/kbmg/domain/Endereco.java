package br.com.kbmg.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.kbmg.enums.StatusEnum;
import br.com.kbmg.enums.TipoEnderecoEnum;

@Entity
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEndereco;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PESSOA_ID")
	@JsonIgnore
	private Pessoa pessoa;
	
	private String cep;

	@NotBlank
	private String logradouro;
	
	private String complemento;
	
	@NotBlank
	private String bairro;

	@NotBlank
	private String numero;

	private String localidade;
	private String uf;
	private Integer ibge;

	@NotNull(message = "Tipo do endereço inválido.")
	private TipoEnderecoEnum tipoEndereco;

	@NotNull
	private LocalDate dataModificacao;

	@NotNull
	private StatusEnum status;
	
	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Integer getIbge() {
		return ibge;
	}

	public void setIbge(Integer ibge) {
		this.ibge = ibge;
	}

	public TipoEnderecoEnum getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEnderecoEnum tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public LocalDate getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(LocalDate dataModificacao) {
		this.dataModificacao = dataModificacao;
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
		result = prime * result + ((idEndereco == null) ? 0 : idEndereco.hashCode());
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
		Endereco other = (Endereco) obj;
		if (idEndereco == null) {
			if (other.idEndereco != null)
				return false;
		} else if (!idEndereco.equals(other.idEndereco))
			return false;
		return true;
	}

}
