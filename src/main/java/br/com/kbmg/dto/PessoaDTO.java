package br.com.kbmg.dto;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.kbmg.enums.TipoPessoa;

public class PessoaDTO {

	private String idPessoa;
	private String nomeCompleto;

	private TipoPessoa tipoPessoa;
	private PessoaFisicaDto pessoaFisica;
	private PessoaJuridicaDto pessoaJuridica;

	private Set<EmailDTO> emails = new LinkedHashSet<>();
	private Set<EnderecoDto> enderecos = new LinkedHashSet<>();
	private Set<TelefoneDto> telefones = new LinkedHashSet<>();

	public String getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(String idPessoa) {
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

	public PessoaFisicaDto getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisicaDto pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public PessoaJuridicaDto getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridicaDto pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public Set<EmailDTO> getEmails() {
		return emails;
	}

	public void setEmails(Set<EmailDTO> emails) {
		this.emails = emails;
	}

	public Set<EnderecoDto> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<EnderecoDto> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<TelefoneDto> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<TelefoneDto> telefones) {
		this.telefones = telefones;
	}

}
