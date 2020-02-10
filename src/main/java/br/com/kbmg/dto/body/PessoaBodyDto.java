package br.com.kbmg.dto.body;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class PessoaBodyDto {

	@NotBlank
	private String nomeCompleto;

	@NotBlank
	private String cpfCnpj;
	
	private String rg;

	private String inscricaoEstadual;

	@Valid
	@NotEmpty(message = "Obrigatório informar pelo menos um email.")
	private Set<EmailBodyDto> emails = new LinkedHashSet<>();

	@Valid
	@NotEmpty(message = "Obrigatório informar pelo menos um endereco.")
	private Set<EnderecoBodyDto> enderecos = new LinkedHashSet<>();
	
	@Valid
	@NotEmpty(message = "Obrigatório informar pelo menos um telefone.")
	private Set<TelefoneBodyDto> telefones = new LinkedHashSet<>();

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public Set<EmailBodyDto> getEmails() {
		return emails;
	}

	public void setEmails(Set<EmailBodyDto> emails) {
		this.emails = emails;
	}

	public Set<EnderecoBodyDto> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<EnderecoBodyDto> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<TelefoneBodyDto> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<TelefoneBodyDto> telefones) {
		this.telefones = telefones;
	}

	
}
