package br.com.kbmg.dto;

import br.com.kbmg.enums.TipoDeUsoEnum;

public class TelefoneDto extends BaseCadastroDto{

	private Long idTelefone;
	private Integer ddd;
	private Integer numero;
	private String contato;
	private TipoDeUsoEnum tipoDeUso;
	
	public Long getIdTelefone() {
		return idTelefone;
	}
	public void setIdTelefone(Long idTelefone) {
		this.idTelefone = idTelefone;
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

}
