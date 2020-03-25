package br.com.kbmg.dto.body;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.kbmg.enums.TipoEnderecoEnum;

public class EnderecoBodyDto {

	private String cep;

	@NotBlank(message="logradouro obrigatório")
	private String logradouro;

	private String complemento;

	@NotBlank(message="bairro obrigatório")
	private String bairro;

	@NotBlank(message="numero obrigatório")
	private String numero;

	private String localidade;
	
	@Size(max = 2, message = "UF inválida.")
	private String uf;
	
	private Integer ibge;

	@NotNull(message = "Tipo do endereço inválido.")
	private TipoEnderecoEnum tipoEndereco;

	
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

}
