package br.com.kbmg.dto.body;

import javax.validation.constraints.NotNull;

import br.com.kbmg.enums.TipoDeUsoEnum;

public class TelefoneBodyDto {

	@NotNull(message = "ddd obrigatório")
	private Integer ddd;

	@NotNull(message = "numero obrigatório")
	private Integer numero;

	private String contato;

	@NotNull(message = "Tipo de uso do telefone inválido.")
	private TipoDeUsoEnum tipoDeUso;

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

}
