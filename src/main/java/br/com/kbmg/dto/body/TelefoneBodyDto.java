package br.com.kbmg.dto.body;

import javax.validation.constraints.NotBlank;

import br.com.kbmg.enums.TipoDeUsoEnum;

public class TelefoneBodyDto {

	@NotBlank(message="ddd obrigatório")
	private String ddd;
	
	@NotBlank(message="numero obrigatório")
	private String numero;
	
	private String contato;
	private TipoDeUsoEnum tipoDeUso;
	
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
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
