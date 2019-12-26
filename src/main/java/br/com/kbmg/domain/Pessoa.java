package br.com.kbmg.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.kbmg.enums.TipoPessoa;
import br.com.kbmg.utils.Validator;

@Entity
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPessoa;

	@Column
	@NotBlank(message = "Nome da pessoa obrigatório")
	private String nomeCompleto;

	@Column
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Tipo da pessoa obrigatório.")
	private TipoPessoa tipoPessoa;
	
	@Column
	private Date dataCriacao = new Date();

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "PF_ID")
	private PessoaFisica pessoaFisica;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "PJ_ID")
	private PessoaJuridica pessoaJuridica;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "pessoa", targetEntity = Email.class)
	private Set<Email> emails = new LinkedHashSet<>();

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "pessoa", targetEntity = Endereco.class)
	private Set<Endereco> enderecos = new LinkedHashSet<>();

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "pessoa", targetEntity = Telefone.class)
	private Set<Telefone> telefones = new LinkedHashSet<>();

	public Pessoa() {

	}

	public Pessoa(String idPessoa) {
		this.idPessoa = Validator.stringParseLong(idPessoa, "Id da Pessoa");
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPessoa == null) ? 0 : idPessoa.hashCode());
		result = prime * result + ((pessoaFisica == null) ? 0 : pessoaFisica.hashCode());
		result = prime * result + ((pessoaJuridica == null) ? 0 : pessoaJuridica.hashCode());
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
		if (idPessoa == null) {
			if (other.idPessoa != null)
				return false;
		} else if (!idPessoa.equals(other.idPessoa))
			return false;
		if (pessoaFisica == null) {
			if (other.pessoaFisica != null)
				return false;
		} else if (!pessoaFisica.equals(other.pessoaFisica))
			return false;
		if (pessoaJuridica == null) {
			if (other.pessoaJuridica != null)
				return false;
		} else if (!pessoaJuridica.equals(other.pessoaJuridica))
			return false;
		return true;
	}

	
}
