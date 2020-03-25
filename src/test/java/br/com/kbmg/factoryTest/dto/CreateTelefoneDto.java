package br.com.kbmg.factoryTest.dto;

import br.com.kbmg.dto.TelefoneDto;
import br.com.kbmg.enums.TipoDeUsoEnum;

public class CreateTelefoneDto {

	public static TelefoneDto get(Long idTelefone, Integer numero, String contato, TipoDeUsoEnum tipoDeUso,
			String pessoaId) {
		return new TelefoneDto() {
			{
				setIdTelefone(idTelefone);
				setDdd(92);
				setNumero(numero);
				setTipoDeUso(tipoDeUso);
				setPessoaId(pessoaId);
			}
		};
	}
}
